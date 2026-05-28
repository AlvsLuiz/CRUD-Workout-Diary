package controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Company;
import model.ModelException;
import model.User;
import model.dao.CompanyDAO;
import model.dao.DAOFactory;

@WebServlet(urlPatterns = {"/companies", "/company/form", "/company/insert", "/company/update", "/company/delete"})
public class CompaniesController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		String action = req.getRequestURI();
		printURI("GET", action);
		
		if ((req.getContextPath() + "/company/form").equals(action)) {
			
			CommonsController.listUsers(req);
			req.setAttribute("action", "insert");
			
			ControllerUtil.forward(req, resp, "/form-company.jsp");
			return;
		}
		
		if ((req.getContextPath() + "/company/update").equals(action)) {
			CommonsController.listUsers(req);
			req.setAttribute("action", "update");
			
			int companyId = Integer.parseInt(req.getParameter("companyId"));
			loadCompany(req, companyId);
			
			ControllerUtil.forward(req, resp, "/form-company.jsp");
			return;
		}
		
		// Carregar as empresas
		// Colocar no contexto da requisição
		loadCompanies(req);
		
		// Redirecionar para companies.jsp
		ControllerUtil.forward(req, resp, "companies.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		String action = req.getRequestURI();
		printURI("POST", req.getRequestURI());
				
		if((req.getContextPath()+"/company/delete").equals(action)) {
			delete(req, resp);
		}
		
		if((req.getContextPath()+"/company/insert").equals(action)) {
			insert(req, resp);
		}
		
		if((req.getContextPath()+"/company/update").equals(action)) {
			update(req, resp);
		}
		
		
		
		
		ControllerUtil.redirect(resp, req.getContextPath() + "/companies");
	}
	
	private void delete(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		printURI("DELETE", req.getRequestURI());
		
		String companyIdStr = req.getParameter("id");
		String companyName = req.getParameter("description");
		
		if (companyIdStr == null || companyIdStr.isBlank()) {
			ControllerUtil.errorMessage(req, 
					"Nenhuma empresa selecionada para exclusão.");
			return;
		}
		
		int companyId = Integer.parseInt(companyIdStr);
		
		CompanyDAO dao = DAOFactory.createDAO(CompanyDAO.class);
		
		try {
			dao.delete(new Company(companyId));
			
			ControllerUtil.sucessMessage(req, 
					String.format("Empresa '%s' excluída com sucesso.", 
							       companyName));
		} catch (ModelException e) {
			ControllerUtil.errorMessage(req, 
					String.format("Houve um erro ao excluir a empresa '%s'.", 
							       companyName));
		}
		
	}
	
	private void insert(HttpServletRequest req, HttpServletResponse resp) {
		Company company = new Company();
		
		setupCompany(company, req);
		
		CompanyDAO dao = DAOFactory.createDAO(CompanyDAO.class);
		
		try {
			String message;
			dao.save(company);
			message = String.format(
					"Empresa '%s' cadastrada com sucesso.", 
					company.getName());
			
			ControllerUtil.sucessMessage(req, message);
		} catch (ModelException e) {
			ControllerUtil.errorMessage(req, 
					"Houve um erro ao inserir os dados da empresa.");
		}
	}
	
	private void update(HttpServletRequest req, HttpServletResponse resp) {
		String companyIdStr = req.getParameter("company-id");
		int companyId = Integer.parseInt(companyIdStr);
		Company company = new Company(companyId);
		
		setupCompany(company, req);
		
		CompanyDAO dao = DAOFactory.createDAO(CompanyDAO.class);
		
		try {
			String message;
			dao.update(company);
			message = String.format(
					"Empresa '%s' alterada com sucesso.", 
					company.getName());
			
			ControllerUtil.sucessMessage(req, message);
		} catch (ModelException e) {
			ControllerUtil.errorMessage(req, 
					"Houve um erro ao alterar os dados da empresa.");
		}
	}
	
	private void setupCompany(Company company, HttpServletRequest req) {
		
		if (company == null)
			return;
		
		String companyName = req.getParameter("company-name");
		String userRole = req.getParameter("user-role");
		String startDate = req.getParameter("user-start");
		String endDate = req.getParameter("user-end");
		int responsibleId = Integer.parseInt(req.getParameter("responsible"));
		
		company.setName(companyName);
		company.setRole(userRole);
		
		if (!"".equals(startDate)) {
			String[]startDates = startDate.split("-");
			company.setStart(new Date(
					Integer.parseInt(startDates[0]) - 1900, 
					Integer.parseInt(startDates[1]) - 1,
					Integer.parseInt(startDates[2]))
			);
		}

		if(!"".equals(endDate)) {
			String[]endDates = endDate.split("-");
			company.setEnd(new Date(
					Integer.parseInt(endDates[0]) - 1900, 
					Integer.parseInt(endDates[1]) - 1,
					Integer.parseInt(endDates[2]))
			);
		}
		
		company.setUser(new User(responsibleId));
	}
	
	private void loadCompanies(HttpServletRequest req) {
		
		CompanyDAO dao = DAOFactory.createDAO(CompanyDAO.class);
		
		List<Company> companies = List.of();
		try {
			companies = dao.listAll();
		} catch (ModelException e) {
			ControllerUtil.errorMessage(req, 
					"Houve um erro ao carregar os dados das empresas.");
		}
		
		req.setAttribute("listaEmpresas", companies);
	}
	
	private void loadCompany(HttpServletRequest req, int companyId) {
		CompanyDAO dao = DAOFactory.createDAO(CompanyDAO.class);
		
		Company companyToEdit = null;
		try {
			companyToEdit = dao.findById(companyId);
		} catch (ModelException e) {
			ControllerUtil.errorMessage(req, 
					"Houve um erro ao buscar dados da empresa a ser atualizada.");
		}
		
		req.setAttribute("company_to_edit", companyToEdit);
	}
	
	private void printURI(String method, String action) {
		System.out.println(method + " " + action);		
	}
}
