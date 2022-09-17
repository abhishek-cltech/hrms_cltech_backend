package com.cltech.hrms.excelupload.helper;

import org.apache.commons.codec.language.bm.Languages;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.cltech.hrms.bean.Address;
import com.cltech.hrms.bean.Education;
import com.cltech.hrms.bean.Employee;
import com.cltech.hrms.bean.EmployeeDetail;
import com.cltech.hrms.bean.Experience;
import com.cltech.hrms.bean.Language;
import com.cltech.hrms.bean.Post;
import com.cltech.hrms.bean.Project;
import com.cltech.hrms.bean.Skill;
import com.cltech.hrms.bean.SocialMedialLinks;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {

	// Check is file excel is not
	public static boolean checkExcelFormat(MultipartFile file) {
		String contentType = file.getContentType();
		if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
			return true;
		} else {
			return false;
		}
	}

	// convert excel to list of emoployee

	public static List<Employee> convertExcelToEmployee(InputStream is) {
		List<Employee> list = new ArrayList<>();
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(is);
			XSSFSheet sheet = workbook.getSheet("Employees");

			int rowNumber = 0;
			Iterator<Row> iterator = sheet.iterator();
			while (iterator.hasNext()) {
				Row row = iterator.next();
				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}
				Iterator<Cell> cells = row.iterator();
				int cid = 0;

				Employee employee = new Employee();

				EmployeeDetail employeedetail = new EmployeeDetail();
				employee.setEmployeeDetail(employeedetail);

				Address address = new Address();
				List<Address> addresses = new ArrayList<>();
				addresses.add(address);
				employeedetail.setAddreses(addresses);

				Skill skill = new Skill();
				List<Skill> skills = new ArrayList<>();
				skills.add(skill);
				employee.setSkills(skills);

				Experience experience = new Experience();
				List<Experience> exp = new ArrayList<>();
				exp.add(experience);
				employee.setExperiences(exp);

				Education education = new Education();
				List<Education> edu = new ArrayList<>();
				edu.add(education);
				employee.setEducations(edu);

				Project project = new Project();
				List<Project> proj = new ArrayList<>();
				proj.add(project);
				employee.setProjects(proj);

				Language language = new Language();
				List<Language> languages = new ArrayList<>();
				languages.add(language);
				employee.setLanguages(languages);

				SocialMedialLinks socialMedialLinks = new SocialMedialLinks();
				employee.setSocialMediaLinks(socialMedialLinks);

				Post post = new Post();
				List<Post> pos = new ArrayList<>();
				pos.add(post);
				employee.setPosts(pos);

				while (cells.hasNext()) {
					Cell cell = cells.next();
					/* cell.setCellType(CellType.STRING); */
					switch (cid) {

						case 0:
							employee.setCarrierObjective(cell.getStringCellValue());
							break;
						case 1:
							employee.getEmployeeDetail().setFirstName(cell.getStringCellValue());
							break;
						case 2:
							employee.getEmployeeDetail().setLastName(cell.getStringCellValue());
							break;
						case 3:
							employee.getEmployeeDetail().setEmail(cell.getStringCellValue());
							break;
						case 4:
							employee.getEmployeeDetail().setAlternateEmail(cell.getStringCellValue());
							break;
						case 5:
							employee.getEmployeeDetail().setPhone(cell.getNumericCellValue() + "");
							break;
						case 6:
							employee.getEmployeeDetail().setAlternatePhone(cell.getNumericCellValue() + "");
							break;
						case 7:
							employee.getEmployeeDetail().setGender(cell.getStringCellValue());
							break;

						case 8: employee.getEmployeeDetail().setDob(cell.getLocalDateTimeCellValue()+"");
							break;
						case 9:
							employee.getEmployeeDetail().getAddreses().get(0).setAddressLine(cell.getStringCellValue());
							break;
						case 10:
							employee.getEmployeeDetail().getAddreses().get(0).setCountry(cell.getStringCellValue());
							break;
						case 11:
							employee.getEmployeeDetail().getAddreses().get(0).setState(cell.getStringCellValue());
							break;
						case 12:
							employee.getEmployeeDetail().getAddreses().get(0).setCity(cell.getStringCellValue());
							break;
						case 13:
							employee.getEmployeeDetail().getAddreses().get(0).setPincode(cell.getNumericCellValue() + "");
							break;
						case 14:
							employee.getSkills().get(0).setSkillName(cell.getStringCellValue());
							break;
						case 15:
							employee.getSkills().get(0).setSkillExp(cell.getNumericCellValue() + "");
							break;
						case 16:
							employee.getExperiences().get(0).setJobTitle(cell.getStringCellValue());
							break;
						case 17:
							employee.getExperiences().get(0).setJobDescription(cell.getStringCellValue());
							break;
						case 18:
							employee.getExperiences().get(0).setOrganizationName(cell.getStringCellValue());
							break;
						/*
						 * case 19:
						 * employee.getExperiences().get(0).setStartDate(cell.getDateCellValue() + "");
						 * break;
						 */
						/*
						 * case 20: employee.getExperiences().get(0).setEndDate(cell.getDateCellValue()
						 * + ""); break;
						 */
						case 19:
							employee.getExperiences().get(0).setStartDate(cell.getLocalDateTimeCellValue()+"");
							break;
						case 20:
							employee.getExperiences().get(0).setEndDate(cell.getLocalDateTimeCellValue()+"");
							break;
						case 21:
							employee.getExperiences().get(0)
									.setCurrentlyWorking(Boolean.parseBoolean(cell.getStringCellValue()));
							break;
						case 22:
							employee.getEducations().get(0).setSchoolName(cell.getStringCellValue());
							break;
						case 23:
							employee.getEducations().get(0).setDegree(cell.getStringCellValue());
							break;
						case 24:
							employee.getEducations().get(0).setFieldOfStudy(cell.getStringCellValue());
							break;
						case 25:
							employee.getEducations().get(0).setLocation(cell.getStringCellValue());
							break;
						case 26:
							employee.getEducations().get(0).setCompletionDate(cell.getDateCellValue() + "");
							break;
						case 27:
							employee.getEducations().get(0)
									.setPassingPercentage(Double.parseDouble(cell.getNumericCellValue() + ""));
							break;
						case 28:
							employee.getProjects().get(0).setProjectName(cell.getStringCellValue());
							break;
						case 29:
							employee.getProjects().get(0).setProjectDescription(cell.getStringCellValue());
							break;
						case 30:
							employee.getLanguages().get(0).setLanguageName(cell.getStringCellValue());
							break;
						case 31:
							employee.getLanguages().get(0).setProficient(cell.getStringCellValue());
							break;
						case 32:
							employee.getLanguages().get(0).setRead(Boolean.parseBoolean(cell.getBooleanCellValue() + " "));
							break;
						case 33:
							employee.getLanguages().get(0).setSpeak(Boolean.parseBoolean(cell.getBooleanCellValue() + " "));
							break;
						case 34:
							employee.getLanguages().get(0).setWrite(Boolean.parseBoolean(cell.getBooleanCellValue() + " "));
							break;
						case 35:
							employee.setWorking(Boolean.parseBoolean(cell.getBooleanCellValue() + ""));
							break;
						case 36:
							employee.setCurrentCTC(Double.parseDouble(cell.getNumericCellValue() + ""));
							break;
						case 37:
							employee.setExpectedCTC(Double.parseDouble(cell.getNumericCellValue() + ""));
							break;
						case 38:
							employee.setPreferedLocation(cell.getStringCellValue());
							break;
						case 39:
							employee.setCertifications(cell.getStringCellValue());
							break;
						case 40:
							employee.setHobbies(cell.getStringCellValue());
							break;
						case 41:
							employee.setTotalSkill(cell.getStringCellValue());
							break;
						case 42:
							employee.setTotalExperience(Double.parseDouble(cell.getNumericCellValue() + ""));
							break;
						case 43:
							employee.getSocialMediaLinks().setGitHub(cell.getStringCellValue());
							break;
						case 44:
							employee.getSocialMediaLinks().setStackOverflow(cell.getStringCellValue());
							break;
						case 45:
							employee.getSocialMediaLinks().setLinkedIn(cell.getStringCellValue());
							break;
//					  case 46:
//						  employee.getPosts().get(0).setDepartmentId((long)cell.getNumericCellValue());
//					  break;
						case 46:
							employee.getPosts().get(0).setDepartmentName(cell.getStringCellValue());
							break;
						default:
							break;
					}
					cid++;
				}
				list.add(employee);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
