import departments.Department;
import departments.DepartmentService;
import members.Member;
import members.MemberService;
import org.sql2o.Sql2o;
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
        DepartmentService departmentService = new DepartmentService(sql2o);
        MemberService memberService = new MemberService(sql2o);

        get("/",(req,res) -> {
            Map<String,Object> model  = new HashMap<>();
            List<Department> departments = departmentService.getAllDepartments();
            model.put("departments",departments);
            List<Member> members = memberService.getAllMembers();
            model.put("members",members);
            return new ModelAndView(model,"index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/departments",(req,res)->{
            Map<String,Object> model     = new HashMap<>();
            List<Department> departments = departmentService.getAllDepartments();
            model.put("departments",departments);
            return new ModelAndView(model,"department.hbs");
        }, new HandlebarsTemplateEngine());

        post("/departments", (req, res) -> {
            String name = req.queryParams("name");
            Department department = new Department(name);
            departmentService.addDepartment(department);
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
            res.redirect("/departments");
            return null;
        }, new HandlebarsTemplateEngine());

        get("/departments/:id/members", (req, res) -> {
            Map<String,Object> model = new HashMap<>();
            int departmentId = Integer.parseInt(req.params("id"));
            Department department = departmentService.getDepartmentById(departmentId);
            model.put("department",department);
            List<Member> members  = departmentService.getAllDepartmentMembers(departmentId);
            model.put("members",members);
            return new ModelAndView(model,"department_members.hbs");
        }, new HandlebarsTemplateEngine());

        post("/departments/:id/members", (req, res) -> {
            Map<String,Object> model = new HashMap<>();
            int departmentId = Integer.parseInt(req.params("id"));
            String firstName = req.queryParams("firstName");
            String lastName  = req.queryParams("lastName");
            Member member = new Member(firstName,lastName,departmentId);
            memberService.addMember(member);
            res.redirect("/departments/"+departmentId+"/members");
            return null;
        }, new HandlebarsTemplateEngine());

        get("/departments/:id/delete", (req, res) -> {
            int departmentId = Integer.parseInt(req.params("id"));
            departmentService.deleteDepartmentById(departmentId);
            res.redirect("/departments");
            return null;
        }, new HandlebarsTemplateEngine());

        get("/staff",(req,res)->{
            Map<String,Object> model = new HashMap<>();
            List<Department> departments = departmentService.getAllDepartments();
            model.put("departments",departments);
            List<Member> members = memberService.getAllMembers();
            model.put("members",members);
            return new ModelAndView(model,"staff.hbs");
        }, new HandlebarsTemplateEngine());

        post("/staff", (req, res) -> {
            String firstName = req.queryParams("firstName");
            String lastName  = req.queryParams("lastName");
            int departmentId = Integer.parseInt(req.queryParams("departmentId"));
            Member member = new Member(firstName,lastName,departmentId);
            memberService.addMember(member);
            res.redirect("/staff");
            return null;
        }, new HandlebarsTemplateEngine());

        get("/staff/:id", (req, res) -> {
            Map<String,Object> model = new HashMap<>();
            int memberId  = Integer.parseInt(req.params("id"));
            Member member = memberService.getMemberById(memberId);
            model.put("member",member);
            Department department = departmentService.getDepartmentById(member.getDepartmentId());
            model.put("department",department);
            List<Department> departments = departmentService.getAllDepartments();
            model.put("departments",departments);
            return new ModelAndView(model,"edit_staff.hbs");
        }, new HandlebarsTemplateEngine());

        post("/staff/:id", (req, res) -> {
            Map<String,Object> model = new HashMap<>();
            int memberId     = Integer.parseInt(req.params("id"));
            String firstName = req.queryParams("firstName");
            String lastName  = req.queryParams("lastName");
            int departmentId = Integer.parseInt(req.queryParams("departmentId"));
            memberService.updateMember(memberId,firstName,lastName,departmentId);
            res.redirect("/staff");
            return null;
        }, new HandlebarsTemplateEngine());

        get("/staff/:id/delete", (req, res) -> {
            int memberId = Integer.parseInt(req.params("id"));
            memberService.deleteMemberById(memberId);
            res.redirect("/staff");
            return null;
        }, new HandlebarsTemplateEngine());
    }
}
