package mk.ukim.finki.wp.kol2022.g2.service.impl;

import mk.ukim.finki.wp.kol2022.g2.model.Course;
import mk.ukim.finki.wp.kol2022.g2.model.Student;
import mk.ukim.finki.wp.kol2022.g2.model.StudentType;
import mk.ukim.finki.wp.kol2022.g2.model.exceptions.InvalidStudentIdException;
import mk.ukim.finki.wp.kol2022.g2.repository.CourseRepository;
import mk.ukim.finki.wp.kol2022.g2.repository.StudentRepository;
import mk.ukim.finki.wp.kol2022.g2.service.CourseService;
import mk.ukim.finki.wp.kol2022.g2.service.StudentService;
import org.eclipse.jetty.util.security.Password;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService, UserDetailsService
{
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CourseService courseService;

    @Override
    public List<Student> listAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findById(Long id) {
        return studentRepository.findById(id).orElseThrow(InvalidStudentIdException::new);
    }

    @Override
    public Student create(String name, String email, String password, StudentType type, List<Long> courseId, LocalDate enrollmentDate) {
        List<Course> courseList = courseRepository.findAllById(courseId);
        Student student = new Student(name,email,passwordEncoder.encode(password),type,courseList,enrollmentDate);
        return studentRepository.save(student);

    }

    @Override
    public Student update(Long id, String name, String email, String password, StudentType type, List<Long> coursesId, LocalDate enrollmentDate) {
       Student student = this.findById(id);
        List<Course> courseList = courseRepository.findAllById(coursesId);

        student.setName(name);
        student.setEmail(email);
        student.setPassword(passwordEncoder.encode(password));
        student.setType(type);
        student.setCourses(courseList);
        student.setEnrollmentDate(enrollmentDate);

        return studentRepository.save(student);
    }

    @Override
    public Student delete(Long id) {
        Student student = this.findById(id);
        studentRepository.delete(student);
        return student;
    }

    @Override
    public List<Student> filter(Long courseId, Integer yearsOfStudying) {
        if(courseId==null && yearsOfStudying==null)
        {
            return listAll();
        }
        else if(courseId==null)
        {
            return studentRepository.findByEnrollmentDateBefore(LocalDate.now().minusYears(yearsOfStudying));
        }
        else if(yearsOfStudying==null)
        {
            return studentRepository.findAllByCoursesContaining(courseService.findById(courseId));
        }
        else
        {
            return studentRepository.findAllByCoursesContainingAndAndEnrollmentDateBefore(courseService.findById(courseId),LocalDate.now().minusYears(yearsOfStudying));
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student student = studentRepository.findByEmail(username);

       return User.builder()
                .username(student.getEmail())
                .password(student.getPassword())
                .authorities("ROLE_"+student.getType())
                .build();
    }
}
