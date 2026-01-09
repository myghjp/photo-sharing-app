package portfolio.PhotoSharingApp.controller.member;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import portfolio.PhotoSharingApp.entity.Account;
import portfolio.PhotoSharingApp.entity.Group;
import portfolio.PhotoSharingApp.entity.Member;
import portfolio.PhotoSharingApp.form.member.AddMemberForm;
import portfolio.PhotoSharingApp.service.account.AccountService;
import portfolio.PhotoSharingApp.service.member.MemberService;

@Controller
@SessionAttributes(value = {"group"})
public class AddMemberController {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private AccountService accountService;
	
	@GetMapping("/add-member")
	public String getAddMember(Model model
			,AddMemberForm form
			
			) {
			
		model.addAttribute("addMemberForm",form);
		
		return "member/add-member";
	}
	
	@PostMapping("/add-member")
	public String postAddMember(
			@ModelAttribute @Validated AddMemberForm form
			,BindingResult bindingResult
			,RedirectAttributes redirectAttributes
			,@ModelAttribute("group")Group group
			/*,HttpSession session*/
			,Model model
			) {
				/*Group group = (Group)session.getAttribute("group"); */
	
		/*※*/
		Account account = modelMapper.map(form, Account.class);
		/*変換をやめて受けとる？*/
		
		/*アカウントIdが存在するかを確認*/
		if (memberService.isExistingAccountId(account.getEmailAddress())) {
			bindingResult.rejectValue("emailAddress", "addMembersEmailError");
		}
		
		/*このグループ内にいる利用者のアドレスが重複していないかを確認*/
		if (memberService.isExistingMembersId(account.getEmailAddress(),group)) {
			bindingResult.rejectValue("emailAddress", "addMembersEmailError2");
		}
		/*ーーーーーーーーーーーーーーーーーーーーーーーーーーーーー*/
		/*このグループの管理者のメールアドレスならtrueを返したい*/
		
		/*controllerで比較してみる？*/
		/*@ModelAttribute(form指定)*/
		
		/*if (memberService.isExisting(group.getAccountId(),account.getEmailAddress())) {
			bindingResult.rejectValue("emailAddress", "addMembersEmailError3");
		}*/
		
		if (accountService.isExisting(group.getAccountId(),account.getEmailAddress())) {
			bindingResult.rejectValue("emailAddress", "addMembersEmailError3");
		}
		
		/*ーーーーーーーーーーーーーーーーーーーーーーーーーーーーー*/
		
		if (bindingResult.hasErrors()) {
			/*return getAddMember(model, form);*/
			return "member/add-member";
		}
		
		Member member = new Member();
		
		member.setGroupId(group.getId());
		member.setAccountId(memberService.selectAccountId(account.getEmailAddress()));
		/*member.setAccountId(memberService.selectAccountId(email);*/
		
		memberService.insertMember(member);
		
		return "redirect:list-member";
	}
}