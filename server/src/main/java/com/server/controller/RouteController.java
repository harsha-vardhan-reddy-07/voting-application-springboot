package com.server.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.models.AdminModel;
import com.server.models.ContestantModel;
import com.server.models.ElectionModel;
import com.server.models.OrganizationsModel;
import com.server.models.UsersModel;
import com.server.repos.AdminRepo;
import com.server.repos.ContestantRepo;
import com.server.repos.ElectionRepo;
import com.server.repos.OrganizationsRepo;
import com.server.repos.UsersRepo;


@CrossOrigin(origins = "http://localhost:5173")
@RestController
@Controller
public class RouteController {

    @Autowired
    UsersRepo usersRepo;

    @Autowired
    AdminRepo adminRepo;

    @Autowired
    ContestantRepo contestantRepo;

    @Autowired
    ElectionRepo electionRepo;

    @Autowired
    OrganizationsRepo organizationsRepo;


    @PostMapping("/user-register")
    public UsersModel registerMethod(@RequestBody UsersModel userData) {
        try {
            UsersModel user = usersRepo.save(userData);
            return user;
        } catch (Exception e) {
            return null;
        }
    }


    @PostMapping("/user-login")
    public UsersModel loginMethod(@RequestBody UsersModel userData) {
        try {
            UsersModel user = usersRepo.findByEmail(userData.getEmail());
            if (user.getPassword().equals(userData.getPassword())) {

                return user;
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }


    @PostMapping("/organization-register")
    public OrganizationsModel orgRegisterMethod(@RequestBody OrganizationsModel userData) {
        try {
            OrganizationsModel user = organizationsRepo.save(userData);
            return user;
        } catch (Exception e) {
            return null;
        }
    }


    @PostMapping("/organization-login")
    public OrganizationsModel orgLoginMethod(@RequestBody OrganizationsModel userData) {
        try {
            OrganizationsModel user = organizationsRepo.findByEmail(userData.getEmail());
            if (user.getPassword().equals(userData.getPassword())) {

                return user;
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }


    @PostMapping("/admin-register")
    public AdminModel adminRegisterMethod(@RequestBody AdminModel userData) {
        try {
            AdminModel user = adminRepo.save(userData);
            return user;
        } catch (Exception e) {
            return null;
        }
    }


    @PostMapping("/admin-login")
    public AdminModel adminLoginMethod(@RequestBody AdminModel userData) {
        try {
            AdminModel user = adminRepo.findByEmail(userData.getEmail());
            if (user.getPassword().equals(userData.getPassword())) {

                return user;
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }


    @GetMapping("/users")
    public List<UsersModel> fetchUsersMethod(){
        try {

            List<UsersModel> users = usersRepo.findAll();
            return users;

        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/organizations")
    public List<OrganizationsModel> fetchOrganizationsMethod(){
        try {

            List<OrganizationsModel> organizations = organizationsRepo.findAll();
            return organizations;

        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/elections")
    public List<ElectionModel> fetchElectionsMethod(){
        try {

            List<ElectionModel> elections = electionRepo.findAll();
            return elections;

        } catch (Exception e) {
            return null;
        }
    }


    @GetMapping("/election/{id}")
    public ElectionModel fetchElectionByIdMethod(@PathVariable("id") String id){
        try {

            Optional<ElectionModel> election = electionRepo.findById(id);
            return election.get();

        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping("/create-election")
    public ElectionModel createElectionMethod(@RequestBody ElectionModel electionData) {
        try {
            ElectionModel election = electionRepo.save(electionData);

            Optional<OrganizationsModel> organization = organizationsRepo.findById(electionData.getOrganizationId());
            OrganizationsModel org = organization.get();
            org.getElections().add(election.get_id());
            organizationsRepo.save(org);

            return election;
        } catch (Exception e) {
            return null;
        }
    }


    @PutMapping("/close-election/{id}")
    public ElectionModel closeElectionMethod(@PathVariable("id") String id) {
        try {
            Optional<ElectionModel> election = electionRepo.findById(id);
            ElectionModel elec = election.get();
            elec.setStatus("closed");
            ElectionModel updatedElection = electionRepo.save(elec);
            return updatedElection;
        } catch (Exception e) {
            return null;
        }
    }


    @PostMapping("/apply-contestant")
    public ContestantModel applyContestantMethod(@RequestBody ContestantModel contestantData) {
        try {
            Optional<ElectionModel> election = electionRepo.findById(contestantData.getElectionId());
            ElectionModel elec = election.get();

            Optional<UsersModel> user = usersRepo.findById(contestantData.getContestantId());
            UsersModel usr = user.get();

            contestantData.setUserId(usr.getUserId());
            contestantData.setOrganization(elec.getOrganization());
            contestantData.setOrganizationId(elec.getOrganizationId());

            contestantRepo.save(contestantData);

            elec.getAppliedCandidates().add(contestantData.get_id());

            electionRepo.save(elec);

            return contestantData;
        } catch (Exception e) {
            return null;
        }
    }

    @PutMapping("/approve-contestant/{id}")
    public ContestantModel approveContestantMethod(@PathVariable("id") String id) {
        try {
            Optional<ContestantModel> contestant = contestantRepo.findById(id);
            ContestantModel cont = contestant.get();
            cont.setStatus("approved");
            ContestantModel updatedContestant = contestantRepo.save(cont);

            Optional<ElectionModel> election = electionRepo.findById(cont.getElectionId());
            ElectionModel elec = election.get();
            elec.getCandidates().add(new ContestantModel(cont.getElectionId(), cont.getContestantName(), cont.getUserId()));
            electionRepo.save(elec);

            return updatedContestant;
        } catch (Exception e) {
            return null;
        }
    }

    @PutMapping("/disapprove-contestant/{id}")
    public ContestantModel disapproveContestantMethod(@PathVariable("id") String id) {
        try {
            Optional<ContestantModel> contestant = contestantRepo.findById(id);
            ContestantModel cont = contestant.get();
            cont.setStatus("disapproved");
            ContestantModel updatedContestant = contestantRepo.save(cont);
            return updatedContestant;
        } catch (Exception e) {
            return null;
        }
    }


    @GetMapping("/contestants")
    public List<ContestantModel> fetchContestantsMethod(){
        try {

            List<ContestantModel> contestants = contestantRepo.findAll();
            return contestants;

        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/contestants/{id}")
    public ContestantModel fetchContestantByIdMethod(@PathVariable("id") String id){
        try {

            List<ContestantModel> contestant = contestantRepo.findAllByElectionId(id);
            return contestant.get(0);

        } catch (Exception e) {
            return null;
        }
    }


    @PostMapping("/vote")
    public ContestantModel voteMethod(@RequestBody String requestBody) {
        try {

            System.out.println(requestBody);

            ObjectMapper mapper = new ObjectMapper();
            Map<String, String> data = mapper.readValue(requestBody, Map.class);

            // Access the electionId value
            String electionId = data.get("electionId");
            String candidateId = data.get("candidateId");
            String voterId = data.get("voterId");

            System.out.println( electionId);

            Optional<ElectionModel> election = electionRepo.findById(electionId);
            ElectionModel elec = election.get();

            elec.getVoters().add(voterId);
            electionRepo.save(elec);

            ContestantModel contestant = contestantRepo.findByUserId(candidateId);
            System.out.println("Alexaaa.... join here....");
            System.out.println(contestant);
            contestant.setVotesCount(contestant.getVotesCount().intValue() + 1);
            contestant.getVotes().add(voterId);

            ContestantModel updatedContestant = contestantRepo.save(contestant);

            return updatedContestant;

        } catch (Exception e) {
            return null;
        }
    }

    
}
