import departments.Department;
import departments.DepartmentService;
import members.Member;
import members.MemberPayload;
import members.MemberService;
import org.sql2o.Sql2o;
import roles.Role;
import roles.RoleService;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {

    static int getAssignedServerPort(){
        ProcessBuilder processBuilder = new ProcessBuilder();
        return processBuilder.environment().get("PORT") != null
                ? Integer.parseInt(processBuilder.environment().get("PORT"))
                : 4500;
    }

    public static void main(String[] args) {

        port(getAssignedServerPort());
        staticFileLocation("/public");

        String DB_URL = "jdbc:postgresql://localhost:5432/technest";
        Sql2o sql2o   = new Sql2o(DB_URL, null, null);
        MemberService memberService = new MemberService(sql2o);
        RoleService roleService     = new RoleService(sql2o);
        DepartmentService departmentService = new DepartmentService(sql2o);

        get("/",(req,res) -> {
            Map<String,Object> model  = new HashMap<>();
            List<Department> departments = departmentService.getAllDepartments();
            model.put("departments",departments);
            List<MemberPayload> members = memberService.getAllMembers();
            model.put("members",members);
            return new ModelAndView(model,"index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/departments",(req,res)->{
            Map<String,Object> model     = new HashMap<>();
            List<Department> departments = departmentService.getAllDepartments();
            model.put("departments",departments);
            model.put("createdDepartment",req.session().attribute("createdDepartment"));
            req.session().removeAttribute("createdDepartment");
            model.put("updatedDepartment",req.session().attribute("updatedDepartment"));
            req.session().removeAttribute("updatedDepartment");
            model.put("deletedDepartment",req.session().attribute("deletedDepartment"));
            req.session().removeAttribute("deletedDepartment");
            return new ModelAndView(model,"department.hbs");
        }, new HandlebarsTemplateEngine());

        post("/departments", (req, res) -> {
            String name = req.queryParams("name");
            Department department = new Department(name);
            departmentService.addDepartment(department);
            req.session().attribute("createdDepartment","Department was added successfully!");
            res.redirect("/departments");
            return null;
        }, new HandlebarsTemplateEngine());

        get("/departments/:id", (req, res) -> {
            Map<String,Object> model = new HashMap<>();
            int departmentId = Integer.parseInt(req.params("id"));
            Department department = departmentService.getDepartmentById(departmentId);
            model.put("department",department);
            return new ModelAndView(model,"edit_department.hbs");
        }, new HandlebarsTemplateEngine());

        post("/departments/:id", (req, res) -> {
            Map<String,Object> model = new HashMap<>();
            int departmentId = Integer.parseInt(req.params("id"));
            departmentService.updateDepartment(departmentId,req.queryParams("name"));
            req.session().attribute("updatedDepartment","Department was updated successfully!");
            res.redirect("/departments");
            return null;
        }, new HandlebarsTemplateEngine());

        get("/departments/:id/members", (req, res) -> {
            Map<String,Object> model = new HashMap<>();
            int departmentId = Integer.parseInt(req.params("id"));
            Department department = departmentService.getDepartmentById(departmentId);
            model.put("department",department);
            List<MemberPayload> members  = departmentService.getAllDepartmentMembers(departmentId);
            model.put("members",members);
            List<Role> roles  = roleService.getAllRoles();
            model.put("roles",roles);
            model.put("createdStaff",req.session().attribute("createdStaff"));
            req.session().removeAttribute("createdStaff");
            return new ModelAndView(model,"department_members.hbs");
        }, new HandlebarsTemplateEngine());

        post("/departments/:id/members", (req, res) -> {
            Map<String,Object> model = new HashMap<>();
            int departmentId = Integer.parseInt(req.params("id"));
            String firstName = req.queryParams("firstName");
            String lastName  = req.queryParams("lastName");
            int roleId = Integer.parseInt(req.queryParams("roleId"));
            Member member = new Member(firstName,lastName,departmentId,roleId);
            memberService.addMember(member);
            req.session().attribute("createdStaff","Staff Member was added successfully!");
            res.redirect("/departments/"+departmentId+"/members");
            return null;
        }, new HandlebarsTemplateEngine());

        get("/departments/:id/delete", (req, res) -> {
            int departmentId = Integer.parseInt(req.params("id"));
            departmentService.deleteDepartmentById(departmentId);
            req.session().attribute("deletedDepartment","Department was deleted successfully!");
            res.redirect("/departments");
            return null;
        }, new HandlebarsTemplateEngine());

        get("/staff",(req,res)->{
            Map<String,Object> model = new HashMap<>();
            List<Department> departments = departmentService.getAllDepartments();
            model.put("departments",departments);
            List<Role> roles  = roleService.getAllRoles();
            model.put("roles",roles);
            List<MemberPayload> members = memberService.getAllMembers();
            model.put("members",members);
            model.put("createdStaff",req.session().attribute("createdStaff"));
            req.session().removeAttribute("createdStaff");
            model.put("updatedStaff",req.session().attribute("updatedStaff"));
            req.session().removeAttribute("updatedStaff");
            model.put("deletedStaff",req.session().attribute("deletedStaff"));
            req.session().removeAttribute("deletedStaff");
            model.put("assignedStaff",req.session().attribute("assignedStaff"));
            req.session().removeAttribute("assignedStaff");
            return new ModelAndView(model,"staff.hbs");
        }, new HandlebarsTemplateEngine());

        post("/staff", (req, res) -> {
            String firstName = req.queryParams("firstName");
            String lastName  = req.queryParams("lastName");
            int departmentId = Integer.parseInt(req.queryParams("departmentId"));
            int roleId = Integer.parseInt(req.queryParams("roleId"));
            Member member = new Member(firstName,lastName,departmentId,roleId);
            memberService.addMember(member);
            req.session().attribute("createdStaff","Staff Member was added successfully!");
            res.redirect("/staff");
            return null;
        }, new HandlebarsTemplateEngine());

        get("/staff/:id", (req, res) -> {
            Map<String,Object> model = new HashMap<>();
            int memberId  = Integer.parseInt(req.params("id"));
            Member member = memberService.getMemberById(memberId);
            model.put("member",member);
            Role specificRole     = roleService.getRoleById(member.getRoleId());
            model.put("role",specificRole);
            Department department = departmentService.getDepartmentById(member.getDepartmentId());
            model.put("department",department);
            List<Department> departments = departmentService.getAllDepartments();
            model.put("departments",departments);
            List<Role> roles = roleService.getAllRoles();
            model.put("roles",roles);
            return new ModelAndView(model,"edit_staff.hbs");
        }, new HandlebarsTemplateEngine());

        post("/staff/:id", (req, res) -> {
            Map<String,Object> model = new HashMap<>();
            int memberId     = Integer.parseInt(req.params("id"));
            String firstName = req.queryParams("firstName");
            String lastName  = req.queryParams("lastName");
            int departmentId = Integer.parseInt(req.queryParams("departmentId"));
            int roleId       = Integer.parseInt(req.queryParams("roleId"));
            memberService.updateMember(memberId,firstName,lastName,departmentId,roleId);
            req.session().attribute("updatedStaff","Staff Member was updated successfully!");
            res.redirect("/staff");
            return null;
        }, new HandlebarsTemplateEngine());

        get("/staff/:id/delete", (req, res) -> {
            int memberId = Integer.parseInt(req.params("id"));
            memberService.deleteMemberById(memberId);
            req.session().attribute("deletedStaff","Staff Member was deleted successfully!");
            res.redirect("/staff");
            return null;
        }, new HandlebarsTemplateEngine());

        get("/roles",(req,res)->{
            Map<String,Object> model = new HashMap<>();
            List<Role> roles = roleService.getAllRoles();
            model.put("roles",roles);
            model.put("createdRole",req.session().attribute("createdRole"));
            req.session().removeAttribute("createdRole");
            model.put("updatedRole",req.session().attribute("updatedRole"));
            req.session().removeAttribute("updatedRole");
            model.put("deletedRole",req.session().attribute("deletedRole"));
            req.session().removeAttribute("deletedRole");
            return new ModelAndView(model,"role.hbs");
        }, new HandlebarsTemplateEngine());

        post("/roles", (req, res) -> {
            String name = req.queryParams("name");
            Role role   = new Role(name);
            roleService.addRole(role);
            req.session().attribute("createdRole","Role was added successfully!");
            res.redirect("/roles");
            return null;
        }, new HandlebarsTemplateEngine());

        get("/roles/:id", (req, res) -> {
            Map<String,Object> model = new HashMap<>();
            int roleId = Integer.parseInt(req.params("id"));
            Role role = roleService.getRoleById(roleId);
            model.put("role",role);
            return new ModelAndView(model,"edit_role.hbs");
        }, new HandlebarsTemplateEngine());

        post("/roles/:id", (req, res) -> {
            Map<String,Object> model = new HashMap<>();
            int roleId = Integer.parseInt(req.params("id"));
            roleService.updateRole(roleId,req.queryParams("name"));
            req.session().attribute("updatedRole","Role was updated successfully!");
            res.redirect("/roles");
            return null;
        }, new HandlebarsTemplateEngine());

        get("/roles/:id/delete", (req, res) -> {
            int roleId = Integer.parseInt(req.params("id"));
            roleService.deleteRoleById(roleId);
            req.session().attribute("deletedRole","Role was deleted successfully!");
            res.redirect("/roles");
            return null;
        }, new HandlebarsTemplateEngine());
    }
}
