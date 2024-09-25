package mk.ukim.finki.wp.jan2022.g2.service.impl;

import mk.ukim.finki.wp.jan2022.g2.model.Discussion;
import mk.ukim.finki.wp.jan2022.g2.model.DiscussionTag;
import mk.ukim.finki.wp.jan2022.g2.model.User;
import mk.ukim.finki.wp.jan2022.g2.model.exceptions.InvalidDiscussionIdException;
import mk.ukim.finki.wp.jan2022.g2.repository.DiscussionRepository;
import mk.ukim.finki.wp.jan2022.g2.repository.UserRepository;
import mk.ukim.finki.wp.jan2022.g2.service.DiscussionService;
import mk.ukim.finki.wp.jan2022.g2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class DiscussionServiceImpl implements DiscussionService
{
    @Autowired
    private DiscussionRepository discussionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @Override
    public List<Discussion> listAll() {
        return discussionRepository.findAll();
    }

    @Override
    public Discussion findById(Long id) {
        return discussionRepository.findById(id).orElseThrow(InvalidDiscussionIdException::new);
    }

    @Override
    public Discussion create(String title, String description, DiscussionTag discussionTag, List<Long> participants, LocalDate dueDate) {
        List<User> users = userRepository.findAllById(participants);

        Discussion discussion = new Discussion(title,description,discussionTag,users,dueDate);
        return discussionRepository.save(discussion);
    }

    @Override
    public Discussion update(Long id, String title, String description, DiscussionTag discussionTag, List<Long> participants) {
        Discussion discussion = this.findById(id);
        List<User> users = userRepository.findAllById(participants);

        discussion.setTitle(title);
        discussion.setDescription(description);
        discussion.setTag(discussionTag);
        discussion.setParticipants(users);

        return discussionRepository.save(discussion);
    }

    @Override
    public Discussion delete(Long id) {
        Discussion d1 = this.findById(id);
        discussionRepository.delete(d1);
        return d1;
    }

    @Override
    public Discussion markPopular(Long id) {
        Discussion discussion = this.findById(id);
        discussion.setPopular(true);
        return discussionRepository.save(discussion);
    }

    @Override
    public List<Discussion> filter(Long participantId, Integer daysUntilClosing) {
        if(participantId==null && daysUntilClosing==null)
        {
            return listAll();
        }
        else if(participantId==null)

        {
            return discussionRepository.findAllByDueDateIsBefore(LocalDate.now().plusDays(daysUntilClosing));
        }
        else if(daysUntilClosing==null)
        {
            return discussionRepository.findAllByParticipantsContaining(userService.findById(participantId));
        }
        else
        {
            return discussionRepository.findAllByParticipantsContainingAndDueDateIsBefore(userService.findById(participantId),LocalDate.now().plusDays(daysUntilClosing));
        }
    }
}
