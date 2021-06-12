package com.userservice.service;

import com.userservice.exception.UserNotFoundException;
import com.userservice.model.User;
import com.userservice.model.UserModel;
import com.userservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserService(UserRepository repository, ModelMapper modelMapper) {
        this.userRepository = repository;
        this.modelMapper = modelMapper;
    }

    public List<UserModel> findAllUsers() {
        return userRepository.findAll().stream().map(user -> modelMapper.map(user, UserModel.class)).collect(Collectors.toList());
    }

    public UserModel findById(Long id) throws UserNotFoundException {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(" User id: " + id));
        return modelMapper.map(user, UserModel.class);
    }

    public UserModel saveUser(UserModel userModel) {
        var user = userRepository.save(modelMapper.map(userModel, User.class));
        return modelMapper.map(user, UserModel.class);
    }

    public UserModel patchUser(Long id, Map<String, Object> patchObjects) throws UserNotFoundException {
        var user = findById(id);
        patchObjects.forEach((key, value) -> {
            switch (key) {
                case "name":
                    user.setName(String.valueOf(value));
                    break;
                case "address":
                    user.setAddress(String.valueOf(value));
                    break;
                case "country":
                    user.setCountry(String.valueOf(value));
                    break;
                default:
                    log.error("");
            }
        });

        return saveUser(user);
    }

    public UserModel saveOrUpdateUser(Long id, UserModel userModel) {
        findById(id);
        var user = modelMapper.map(userModel, User.class);
        user.setId(id);
        return modelMapper.map(userRepository.save(user), UserModel.class);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public Map<String, Object> calculations() {
        var users = findAllUsers();
        var groupByDepartment = users.stream().collect(Collectors.groupingBy(UserModel::getDepartment));
        var groupByGender = users.stream().collect(Collectors.groupingBy(UserModel::getGender, Collectors.counting()));
        var highestPaid = users.stream().max(Comparator.comparingDouble(UserModel::getSalary));
        var allDepartments = users.stream().map(UserModel::getDepartment).collect(Collectors.toList());
        var avgAgeOfFemaleAndMale = users.stream().collect(Collectors.groupingBy(UserModel::getGender, Collectors.averagingInt(UserModel::getAge)));
        var joinAfter2015 = users.stream().filter(user -> user.getYearOfJoining() > 2015).collect(Collectors.toList());
        var top5Paid = users.stream().sorted(Comparator.comparingDouble(UserModel::getSalary)).limit(5).collect(Collectors.toList());
        var countUsersByDpt = users.stream().collect(Collectors.groupingBy(UserModel::getDepartment, Collectors.counting()));
        var avgSalByDpt = users.stream().collect(Collectors.groupingBy(UserModel::getDepartment, Collectors.averagingDouble(UserModel::getSalary)));
        var youngestMaleInProductDevelopment =
                        users.stream()
                            .filter(user -> user.getGender().equals("Male") && user.getDepartment().equals("Product Development"))
                            .min(Comparator.comparingInt(UserModel::getAge));
        var mostWorkExperience =
                        users.stream()
                            .min(Comparator.comparingLong(UserModel::getYearOfJoining));
        var countMaleAndFemaleOfSalesMarketingDept =
                        users.stream()
                            .filter(user -> user.getDepartment().equals("Sales And Marketing"))
                            .collect(Collectors.groupingBy(UserModel::getGender, Collectors.counting()));
        var avgAndTotalSalaryOfOrganization =
                        users.stream()
                            .collect(Collectors.summarizingDouble(UserModel::getSalary));
        var partitioningByAge =
                        users.stream()
                            .collect(Collectors.partitioningBy(user -> user.getAge() > 25));
        var map = new HashMap<String, Object>();
        map.put("Group By Department", groupByDepartment);
        map.put("Group by Gender", groupByGender);
        map.put("Highest Paid", highestPaid);
        map.put("All Departments", allDepartments);
        map.put("Avg Age Of Female And Male", avgAgeOfFemaleAndMale);
        map.put("Join After 2015", joinAfter2015);
        map.put("Top 5 Paid", top5Paid);
        map.put("Count Users By Department", countUsersByDpt);
        map.put("Average salary by Department", avgSalByDpt);
        map.put("Youngest Male User In Product Development", youngestMaleInProductDevelopment);
        map.put("Most Work Experience User", mostWorkExperience);
        map.put("count Male And Female Of Sales Marketing Dept", countMaleAndFemaleOfSalesMarketingDept);
        map.put("Organization Summary Statistics", avgAndTotalSalaryOfOrganization);
        map.put("Partitioning By Age 25", partitioningByAge);
        return map;
    }
}
