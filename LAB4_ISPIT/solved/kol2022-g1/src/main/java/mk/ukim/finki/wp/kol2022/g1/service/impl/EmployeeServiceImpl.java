package mk.ukim.finki.wp.kol2022.g1.service.impl;

import mk.ukim.finki.wp.kol2022.g1.model.Employee;
import mk.ukim.finki.wp.kol2022.g1.model.EmployeeType;
import mk.ukim.finki.wp.kol2022.g1.model.Skill;
import mk.ukim.finki.wp.kol2022.g1.model.exceptions.InvalidEmployeeIdException;
import mk.ukim.finki.wp.kol2022.g1.repository.EmployeeRepository;
import mk.ukim.finki.wp.kol2022.g1.repository.SkillRepository;
import mk.ukim.finki.wp.kol2022.g1.service.EmployeeService;
import mk.ukim.finki.wp.kol2022.g1.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class EmployeeServiceImpl implements EmployeeService, UserDetailsService
{
      @Autowired
      private EmployeeRepository employeeRepository;
      @Autowired
      private SkillRepository skillRepository;
      @Autowired
      private PasswordEncoder passwordEncoder;
      @Autowired
      private SkillService skillService;

    @Override
    public List<Employee> listAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElseThrow(InvalidEmployeeIdException::new);
    }

    @Override
    public Employee create(String name, String email, String password, EmployeeType type, List<Long> skillId, LocalDate employmentDate) {
        List<Skill> skills = skillRepository.findAllById(skillId);
        String encryptedPass = passwordEncoder.encode(password);

        Employee employee = new Employee(name,email,encryptedPass,type,skills,employmentDate);
        return employeeRepository.save(employee);

    }

    @Override
    public Employee update(Long id, String name, String email, String password, EmployeeType type, List<Long> skillId, LocalDate employmentDate) {
        Employee employee = this.findById(id);
        String encryptedPass = passwordEncoder.encode(password);
        List<Skill> skills = skillRepository.findAllById(skillId);

        employee.setName(name);
        employee.setEmail(email);
        employee.setPassword(encryptedPass);
        employee.setType(type);
        employee.setSkills(skills);
        employee.setEmploymentDate(employmentDate);

       return employeeRepository.save(employee);

    }

    @Override
    public Employee delete(Long id) {
        Employee employee = this.findById(id);
        employeeRepository.delete(employee);
        return employee;
    }

    @Override
    public List<Employee> filter(Long skillId, Integer yearsOfService) {
        if(skillId==null && yearsOfService==null)
        {
            return listAll();
        }
        else if(skillId==null)
        {
            return employeeRepository.findByEmploymentDateBefore(LocalDate.now().minusYears(yearsOfService));
        }
        else if(yearsOfService==null)
        {
            return employeeRepository.findAllBySkillsContaining(skillService.findById(skillId));
        }
        else
        {
            return employeeRepository
                    .findAllBySkillsContainsAndEmploymentDateBefore(skillService.findById(skillId),
                            LocalDate.now().minusYears(yearsOfService));

        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee e = employeeRepository.findByEmail(username);

        if(e==null)
        {
            throw new UsernameNotFoundException(username);
        }

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                e.getEmail(),
                e.getPassword(),
              Stream.of(new SimpleGrantedAuthority("ROLE_"+ e.getType().name())).collect(Collectors.toList()));
        return userDetails;

       //  return User.builder()
       //         .username(e.getEmail())
       //        .password(e.getPassword())
       //        .authorities("ROLE_"+e.getType().name())
      //         .build();
    }
}
