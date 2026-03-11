package portfolio.PhotoSharingApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import portfolio.PhotoSharingApp.entity.Album;
import portfolio.PhotoSharingApp.entity.Group;
import portfolio.PhotoSharingApp.entity.Member;
import portfolio.PhotoSharingApp.security.LoginUserDetails;
import portfolio.PhotoSharingApp.service.AlbumService;
import portfolio.PhotoSharingApp.service.GroupService;
import portfolio.PhotoSharingApp.service.MemberService;

@Controller
@SessionAttributes(value = {"group"})
public class DashboardController {
	
	@Autowired
	private GroupService groupService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private AlbumService albumService;
	
	@GetMapping("/dashboard")
	public String getDashboard(
			Model model
			,@AuthenticationPrincipal LoginUserDetails user
			,@ModelAttribute("group")Group group
			) {
		
		/*グループ管理者か*/
		if (group.getAccountId() == user.getUserId()) {
			model.addAttribute("isAdmin",true);
		}
		
		/*グループ管理者情報を取得*/
		Group adminInfo = groupService.getAdminInfo(group.getId());
		model.addAttribute("adminInfo", adminInfo);
		
		/*グループメンバーの情報を取得*/
		List<Member> memberList = memberService.getGroupMemberInfo(group.getId());
		model.addAttribute("memberList", memberList);
		
		/*グループアルバムの情報を取得*/
		List<Album> albumList = albumService.getGroupAlbumInfo(group.getId());
		model.addAttribute("albumList", albumList);
		
		return "group/dashboard";
	}
}