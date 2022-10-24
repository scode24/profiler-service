package com.codex.profiler.profilerservice.service;

import com.codex.profiler.profilerservice.entity.*;
import com.codex.profiler.profilerservice.model.Contacts;
import com.codex.profiler.profilerservice.model.ProfileInfoModel;
import com.codex.profiler.profilerservice.model.ProjectData;
import com.codex.profiler.profilerservice.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileManageService {

    private static final String ABOUT_CANDIDATE = "About";
    private static final String ACHIEVEMENTS = "Achievements";
    private static final String ADDRESS = "Address";
    private static final String PHONE_NO = "Phone";
    private static final String PROJECT = "Projects";
    private static final String QUALIFICATION = "Qualifications";
    private static final String SKILL = "Skills";
    private static final String WORKING_EXP = "Working Experiences";
    private static final String LINK = "Link";
    private static final String CONTACTS = "Contacts";
    private static final String MENU = "Menus";


    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private AboutCandidateRepository aboutCandidateRepository;
    @Autowired
    private AchievementRepository achievementRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private PhoneNoRepository phoneNoRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private QualificationRepository qualificationRepository;
    @Autowired
    private SkillRepository skillRepository;
    @Autowired
    private WorkingExpRepository workingExpRepository;
    @Autowired
    private ProjectImageRepository projectImageRepository;
    @Autowired
    private LinkRepository linkRepository;

    public void saveOrUpdateCandidateImage(String email, byte[] image) {
        Candidate candidate = candidateRepository.findByEmail(email);
        candidate.setImage(image);
        candidateRepository.save(candidate);
    }

    public void saveOrUpdateProjectImage(String email, long idProject, byte[] image) {
        projectImageRepository.save(new ProjectImage(idProject, image));
    }


    public List<String> processProfile(ProfileInfoModel profileInfoModel, String email) {
        Candidate candidate = profileRepository.findByEmail(email);
        saveOrUpdateAboutCandidate(candidate.getId(), profileInfoModel.getAbout());
        saveOrUpdateAchievements(candidate.getId(), profileInfoModel.getAchievements());
        saveOrUpdateContacts(candidate.getId(), profileInfoModel.getContacts());
        saveOrUpdateSkills(candidate.getId(), profileInfoModel.getSkills());
        saveOrUpdateQualification(candidate.getId(), profileInfoModel.getQualification());
        saveOrUpdateWorkingExps(candidate.getId(), profileInfoModel.getWorkingExperiences());
        saveOrUpdateLink(candidate.getId(), profileInfoModel.getLinks());
        List<String> projectInfo = saveOrUpdateProjects(candidate.getId(), profileInfoModel.getProjects());

        return projectInfo;
    }

    private void saveOrUpdateLink(long idCandidate, List<Link> links) {
        deleteCandidateData(idCandidate, LINK);

        if (links != null && !links.isEmpty()) {
            for (Link link : links) {
                link.setIdCandidate(idCandidate);
            }
            linkRepository.saveAll(links);
        }
    }

    private void saveOrUpdateWorkingExps(long idCandidate, List<WorkingExp> workingExperiences) {
        deleteCandidateData(idCandidate, WORKING_EXP);

        if (workingExperiences != null && !workingExperiences.isEmpty()) {
            for (WorkingExp workingExp : workingExperiences) {
                workingExp.setIdCandidate(idCandidate);
            }
            workingExpRepository.saveAll(workingExperiences);
            menuRepository.save(new Menu(idCandidate, WORKING_EXP));
        }
    }

    private void saveOrUpdateQualification(long idCandidate, List<Qualification> qualifications) {
        deleteCandidateData(idCandidate, QUALIFICATION);

        if (qualifications != null && !qualifications.isEmpty()) {
            for (Qualification qualification : qualifications) {
                qualification.setIdCandidate(idCandidate);
            }
            qualificationRepository.saveAll(qualifications);
            menuRepository.save(new Menu(idCandidate, QUALIFICATION));
        }
    }

    private List<String> saveOrUpdateProjects(long idCandidate, List<Project> projects) {
        deleteCandidateData(idCandidate, PROJECT);

        if (projects != null && !projects.isEmpty()) {
            for (Project project : projects) {
                project.setIdCandidate(idCandidate);
            }
            projectRepository.saveAll(projects);
            menuRepository.save(new Menu(idCandidate, PROJECT));
        }

        List<Project> allSavedProjects = projectRepository.getProjectsByIdCandidate(idCandidate);
        return allSavedProjects.stream().map(p -> p.getId() + ":" + p.getName()).collect(Collectors.toList());
    }

    private void saveOrUpdateSkills(long idCandidate, List<Skill> skills) {
        deleteCandidateData(idCandidate, SKILL);

        if (skills != null && !skills.isEmpty()) {
            for (Skill skill : skills) {
                skill.setIdCandidate(idCandidate);
            }
            skillRepository.saveAll(skills);
            menuRepository.save(new Menu(idCandidate, SKILL));
        }
    }

    private void saveOrUpdateContacts(long idCandidate, Contacts contacts) {
        deleteCandidateData(idCandidate, PHONE_NO);
        deleteCandidateData(idCandidate, ADDRESS);

        if (contacts.getPhoneNos() != null && !contacts.getPhoneNos().isEmpty()) {
            for (PhoneNo phoneNo : contacts.getPhoneNos()) {
                phoneNo.setIdCandidate(idCandidate);
            }
            phoneNoRepository.saveAll(contacts.getPhoneNos());
        }

        if (contacts.getAddresses() != null && !contacts.getAddresses().isEmpty()) {
            for (Address address : contacts.getAddresses()) {
                address.setIdCandidate(idCandidate);
            }
            addressRepository.saveAll(contacts.getAddresses());
        }
    }

    private void saveOrUpdateAboutCandidate(long idCandidate, String about) {
        deleteCandidateData(idCandidate, ABOUT_CANDIDATE);

        if (about != null) {
            aboutCandidateRepository.save(new AboutCandidate(idCandidate, about));
            menuRepository.save(new Menu(idCandidate, ABOUT_CANDIDATE));
        }
    }

    private void saveOrUpdateAchievements(long idCandidate, List<Achievement> achievements) {
        deleteCandidateData(idCandidate, ACHIEVEMENTS);

        if (achievements != null && !achievements.isEmpty()) {
            for (Achievement achievement : achievements) {
                achievement.setIdCandidate(idCandidate);
            }
            achievementRepository.saveAll(achievements);
            menuRepository.save(new Menu(idCandidate, ACHIEVEMENTS));
        }
    }

    public AboutCandidate getAboutCandidate(String email) {
        return (AboutCandidate) getProfileData(email, ABOUT_CANDIDATE);
    }

    public List<Achievement> getAchievements(String email) {
        return (List<Achievement>) getProfileData(email, ACHIEVEMENTS);
    }

    public Contacts getContacts(String email) {
        return (Contacts) getProfileData(email, CONTACTS);
    }

    public List<Link> getLinks(String email) {
        return (List<Link>) getProfileData(email, LINK);
    }

    public List<Menu> getMenus(String email) {
        return (List<Menu>) getProfileData(email, MENU);
    }

    public List<ProjectData> getProjects(String email) {
        return (List<ProjectData>) getProfileData(email, PROJECT);
    }

    public List<Qualification> getQualification(String email) {
        return (List<Qualification>) getProfileData(email, QUALIFICATION);
    }

    public List<Skill> getSkills(String email) {
        return (List<Skill>) getProfileData(email, SKILL);
    }

    public List<WorkingExp> getWorkingExperiences(String email) {
        return (List<WorkingExp>) getProfileData(email, WORKING_EXP);
    }

    public Candidate getTitleCardInfo(String email) {
        Candidate candidate = candidateRepository.findByEmail(email);
        candidate.setAccessToken("");
        return candidate;
    }


    private void deleteCandidateData(long idCandidate, String title) {
        switch (title) {
            case ABOUT_CANDIDATE:
                aboutCandidateRepository.deleteByIdCandidate(idCandidate);
                break;
            case ACHIEVEMENTS:
                achievementRepository.deleteByIdCandidate(idCandidate);
                break;
            case ADDRESS:
                addressRepository.deleteByIdCandidate(idCandidate);
                break;
            case PHONE_NO:
                phoneNoRepository.deleteByIdCandidate(idCandidate);
                break;
            case PROJECT:
                List<Long> idProjects = projectRepository.getProjectIdsByIdCandidate(idCandidate);
                projectRepository.deleteByIdCandidate(idCandidate);
                projectImageRepository.deleteByIdProjects(idProjects);
                break;
            case QUALIFICATION:
                qualificationRepository.deleteByIdCandidate(idCandidate);
                break;
            case SKILL:
                skillRepository.deleteByIdCandidate(idCandidate);
                break;
            case WORKING_EXP:
                workingExpRepository.deleteByIdCandidate(idCandidate);
                break;
            case LINK:
                linkRepository.deleteByIdCandidate(idCandidate);
                break;
        }
        menuRepository.deleteByIdCandidate(idCandidate, title);
    }

    private Object getProfileData(String email, String title) {
        long idCandidate = candidateRepository.findByEmail(email).getId();
        switch (title) {
            case ABOUT_CANDIDATE:
                return aboutCandidateRepository.findByIdCandidate(idCandidate);
            case ACHIEVEMENTS:
                return achievementRepository.findByIdCandidate(idCandidate);
            case CONTACTS:
                return new Contacts(phoneNoRepository.findByIdCandidate(idCandidate),
                        addressRepository.findByIdCandidate(idCandidate));
            case PROJECT:
                List<ProjectData> projectDataList = new ArrayList<>();
                List<Project> projects = projectRepository.getProjectsByIdCandidate(idCandidate);
                for (Project project : projects) {
                    ProjectData projectData = new ProjectData();
                    projectData.setProjects(project);
                    List<ProjectImage> projectImages = projectImageRepository.getProjectImageByIdProject(project.getId());
                    projectData.setProjectImages(projectImages);
                    projectDataList.add(projectData);
                }
                return projectDataList;

            case QUALIFICATION:
                return qualificationRepository.findByIdCandidate(idCandidate);
            case SKILL:
                return skillRepository.findByIdCandidate(idCandidate);
            case WORKING_EXP:
                return workingExpRepository.findByIdCandidate(idCandidate);
            case LINK:
                return linkRepository.findByIdCandidate(idCandidate);
            case MENU:
                return menuRepository.findByIdCandidate(idCandidate);
            default:
                return null;
        }
    }
}
