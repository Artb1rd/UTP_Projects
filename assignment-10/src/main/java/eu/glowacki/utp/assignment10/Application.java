package eu.glowacki.utp.assignment10;

import eu.glowacki.utp.assignment10.dtos.GroupDTO;
import eu.glowacki.utp.assignment10.dtos.UserDTO;
import eu.glowacki.utp.assignment10.repositories.UserRepository;

import java.sql.*;

public class Application {
    static UserRepository userRepo = new UserRepository();
    static GroupRepository groupRepo = new GroupRepository();

    public static void main(String[] args) {
        userRepo.add(new UserDTO(4,"vablo","pao2009"));
//        userRepo.addOrUpdate(new UserDTO(3, "aleXA", "allo11"));
//        userRepo.delete(new UserDTO(3, "alexis", "allo11"));
        System.out.println(userRepo.findById(4));
//        userRepo.commitTransaction();
//        System.out.println(userRepo.getCount());
//        System.out.println(userRepo.exists(new UserDTO(3, "alexis", "allo11")));
//        userRepo.findByName("artem").forEach(System.out::println);
//        groupRepo.addOrUpdate(new GroupDTO(1, "Bar", "Nice"));
//        System.out.println(groupRepo.findById(1));

    }
}
