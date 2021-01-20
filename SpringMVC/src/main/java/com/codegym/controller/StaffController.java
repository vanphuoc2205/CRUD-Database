package com.codegym.controller;

import com.codegym.model.Project;
import com.codegym.model.Staff;
import com.codegym.repository.ProjectRepository;
import com.codegym.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("staff")
public class StaffController {

    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @GetMapping("list")
    public String getViewList(Model model){
        model.addAttribute("listStaff",staffRepository.findAll());
        return "staff/list";
    }
    @GetMapping("create")
    public String getViewCreate(Model model){
       model.addAttribute("Staff",new Staff());
       Iterable<Staff> staff= staffRepository.findAll();
       model.addAttribute("listProject",projectRepository.findAll());
       return "staff/create";
    }

    @PostMapping("create")
    public String Create(Staff staff){
        Project project = projectRepository.findById(staff.getProjectId()).orElse(null);
        staff.setProject(project);
        staffRepository.save(staff);
        return "redirect:/staff/list";
    }

    @GetMapping("edit/{id}")
    public String getViewEdit(Model model, @PathVariable("id")Long id){
     model.addAttribute("staff",staffRepository.findById(id).orElse(null));
     model.addAttribute("listProject",projectRepository.findAll());
     return "staff/edit";
    }
    @PostMapping("edit")
    public String Edit(Model model,Staff staff){
        Project project=projectRepository.findById(staff.getProjectId()).orElse(null);
        staff.setProject(project);
        staffRepository.save(staff);
        return "redirect:/staff/list";
    }
    @GetMapping("delete/{id}")
    public String Delete(Model model,@PathVariable("id")Long id){
        staffRepository.deleteById(id);
        return "redirect:/staff/list";
    }
}
