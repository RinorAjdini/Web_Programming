package mk.ukim.finki.wp.june2022.g1.service.impl;

import mk.ukim.finki.wp.june2022.g1.model.OSType;
import mk.ukim.finki.wp.june2022.g1.model.User;
import mk.ukim.finki.wp.june2022.g1.model.VirtualServer;
import mk.ukim.finki.wp.june2022.g1.model.exceptions.InvalidVirtualMachineIdException;
import mk.ukim.finki.wp.june2022.g1.repository.UserRepository;
import mk.ukim.finki.wp.june2022.g1.repository.VirtualServerRepository;
import mk.ukim.finki.wp.june2022.g1.service.UserService;
import mk.ukim.finki.wp.june2022.g1.service.VirtualServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VirtualServiceImpl implements VirtualServerService
{
    @Autowired
    private VirtualServerRepository virtualServerRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @Override
    public List<VirtualServer> listAll()
    {
        return virtualServerRepository.findAll();
    }

    @Override
    public VirtualServer findById(Long id) {
        return virtualServerRepository.findById(id).orElseThrow(InvalidVirtualMachineIdException::new);
    }

    @Override
    public VirtualServer create(String name, String ipAddress, OSType osType, List<Long> owners, LocalDate launchDate) {
        List<User> users = userRepository.findAllById(owners);
        VirtualServer virtualServer = new VirtualServer(name,ipAddress,osType,users,launchDate);
        return virtualServerRepository.save(virtualServer);
    }

    @Override
    public VirtualServer update(Long id, String name, String ipAddress, OSType osType, List<Long> owners) {
        List<User> users = userRepository.findAllById(owners);
        VirtualServer virtualServer = this.findById(id);

        virtualServer.setInstanceName(name);
        virtualServer.setIpAddress(ipAddress);
        virtualServer.setOSType(osType);
        virtualServer.setOwners(users);

      return virtualServerRepository.save(virtualServer);
    }

    @Override
    public VirtualServer delete(Long id) {
        VirtualServer v = this.findById(id);
        virtualServerRepository.delete(v);
        return v;
    }

    @Override
    public VirtualServer markTerminated(Long id) {
        VirtualServer v = this.findById(id);
        v.setTerminated(true);
        return virtualServerRepository.save(v);

    }

    @Override
    public List<VirtualServer> filter(Long ownerId, Integer activeMoreThanDays) {
        if(ownerId==null && activeMoreThanDays==null)
        {
            return listAll();
        }
        else if(activeMoreThanDays==null)
        {
            return virtualServerRepository.findAllByOwnersContaining(userService.findById(ownerId));
        }
        else if(ownerId==null)
        {
            return virtualServerRepository.findAllByLaunchDateIsBefore(LocalDate.now().minusDays(activeMoreThanDays));
        }
        else
        {
            return virtualServerRepository.findAllByOwnersContainingAndLaunchDateIsBefore(userService.findById(ownerId),LocalDate.now().minusDays(activeMoreThanDays));
        }
    }
}
