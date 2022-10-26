package com.codex.profiler.profilerservice.controller;

import com.codex.profiler.profilerservice.entity.*;
import com.codex.profiler.profilerservice.exception.NoCandidateExistsException;
import com.codex.profiler.profilerservice.model.Contacts;
import com.codex.profiler.profilerservice.model.ProfileInfoModel;
import com.codex.profiler.profilerservice.model.ProjectData;
import com.codex.profiler.profilerservice.model.ResponseModel;
import com.codex.profiler.profilerservice.repository.CandidateRepository;
import com.codex.profiler.profilerservice.service.ProfileManageService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProfileController {

    @Autowired
    private ProfileManageService profileManageService;
    @Autowired
    private CandidateRepository candidateRepository;

    @ApiOperation(value = "Save/update profile information", notes = "Save/update information. This is to be noted that About and Project Description section can take max 2000 character by default, all other string parameters will take max 255 characters" +
            "This POST requires [access-token] to be provided in header." +
            "[access-token] and [query-param] will be checked before processing.")
    @PostMapping("/saveOrUpdateProfile")
    @ApiImplicitParam(name = "access-token", value = "access-token", required = true, allowEmptyValue = false, paramType = "header")
    public ResponseEntity<ResponseModel> saveOrUpdateProfile(@RequestParam String email, @RequestBody ProfileInfoModel profileInfoModel) throws IOException {
        List<String> info = profileManageService.processProfile(profileInfoModel, urlDecode(email));
        return new ResponseEntity<>(new ResponseModel("Profile data saved/ updated", "Project data : " + info), HttpStatus.OK);
    }

    @ApiOperation(value = "Save/update profile image", notes = "Profile image save/ update information. " +
            "This POST requires [access-token] to be provided in header." +
            "[access-token] and [query-param] will be checked before processing.")
    @PostMapping("/saveOrUpdateProfileImage")
    @ApiImplicitParam(name = "access-token", value = "access-token", required = true, allowEmptyValue = false, paramType = "header")
    public ResponseEntity<ResponseModel> saveOrUpdateProfileImage(@RequestParam String email, @RequestParam("image") MultipartFile imageFile) throws IOException {
        profileManageService.saveOrUpdateCandidateImage(urlDecode(email), imageFile.getBytes());
        return new ResponseEntity<>(new ResponseModel("Profile image saved/ updated", null), HttpStatus.OK);
    }

    @ApiOperation(value = "Save/update project image", notes = "Project image save/ update information. " +
            "This POST requires [access-token] to be provided in header." +
            "[access-token] and [query-param] will be checked before processing.")
    @PostMapping("/saveOrUpdateProjectImage")
    @ApiImplicitParam(name = "access-token", value = "access-token", required = true, allowEmptyValue = false, paramType = "header")
    public ResponseEntity<ResponseModel> saveOrUpdateProjectImage(@RequestParam String email, @RequestParam("idProject") long idProject, @RequestParam("image") MultipartFile imageFile) throws IOException {
        profileManageService.saveOrUpdateProjectImage(urlDecode(email), idProject, imageFile.getBytes());
        return new ResponseEntity<>(new ResponseModel("Project image saved/ updated", null), HttpStatus.OK);
    }

    @ApiOperation(value = "Get about candidate by email id", notes = "Returns about candidate")
    @GetMapping("/getAboutCandidate")
    public AboutCandidate getAboutCandidate(@RequestParam String email) throws NoCandidateExistsException, UnsupportedEncodingException {
        return profileManageService.getAboutCandidate(urlDecode(email));
    }

    @ApiOperation(value = "Get achievements by email id", notes = "Returns candidate's all achievements information")
    @GetMapping("/getAchievements")
    public List<Achievement> getAchievements(@RequestParam String email) throws NoCandidateExistsException, UnsupportedEncodingException {
        return profileManageService.getAchievements(urlDecode(email));
    }

    @ApiOperation(value = "Get contacts by email id", notes = "Returns candidate's all contacts information")
    @GetMapping("/getContacts")
    public Contacts getContacts(@RequestParam String email) throws NoCandidateExistsException, UnsupportedEncodingException {
        return profileManageService.getContacts(urlDecode(email));
    }

    @ApiOperation(value = "Get links by email id", notes = "Returns candidate's all links information")
    @GetMapping("/getLinks")
    public List<Link> getLinks(@RequestParam String email) throws NoCandidateExistsException, UnsupportedEncodingException {
        return profileManageService.getLinks(urlDecode(email));
    }

    @ApiOperation(value = "Get menus by email id", notes = "Returns candidate's available menu information")
    @GetMapping("/getMenus")
    public List<Menu> getMenus(@RequestParam String email) throws NoCandidateExistsException, UnsupportedEncodingException {
        return profileManageService.getMenus(urlDecode(email));
    }

    @ApiOperation(value = "Get projects by email id", notes = "Returns candidate's project information")
    @GetMapping("/getProjects")
    public List<ProjectData> getProjects(@RequestParam String email) throws NoCandidateExistsException, UnsupportedEncodingException {
        return profileManageService.getProjects(urlDecode(email));
    }

    @ApiOperation(value = "Get qualification by email id", notes = "Returns candidate's qualification information")
    @GetMapping("/getQualification")
    public List<Qualification> getQualification(@RequestParam String email) throws NoCandidateExistsException, UnsupportedEncodingException {
        return profileManageService.getQualification(urlDecode(email));
    }

    @ApiOperation(value = "Get skills by email id", notes = "Returns candidate's skills information")
    @GetMapping("/getSkills")
    public List<Skill> getSkills(@RequestParam String email) throws NoCandidateExistsException, UnsupportedEncodingException {
        return profileManageService.getSkills(urlDecode(email));
    }

    @ApiOperation(value = "Get working experiences by email id", notes = "Returns candidate's working experiences information")
    @GetMapping("/getWorkingExperiences")
    public List<WorkingExp> getWorkingExperiences(@RequestParam String email) throws NoCandidateExistsException, UnsupportedEncodingException {
        return profileManageService.getWorkingExperiences(urlDecode(email));
    }

    @ApiOperation(value = "Get candidate's name, email, image by email id", notes = "Returns candidate's candidate's name, email, image")
    @GetMapping("/getTitleCardInfo")
    public Candidate getTitleCardInfo(@RequestParam String email) throws NoCandidateExistsException, UnsupportedEncodingException {
        return profileManageService.getTitleCardInfo(urlDecode(email));
    }

    private Candidate checkCandidateExists(String email) throws NoCandidateExistsException {
        Candidate candidate = candidateRepository.findByEmail(email);
        if (candidate == null)
            throw new NoCandidateExistsException("No candidate exists in the record with this email id");
        return candidate;
    }

    private String urlDecode(String email) throws UnsupportedEncodingException {
        return URLDecoder.decode(email, "UTF-8");
    }
}
