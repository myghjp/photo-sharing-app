package portfolio.PhotoSharingApp.controller.group;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import portfolio.PhotoSharingApp.entity.Album;
import portfolio.PhotoSharingApp.entity.Group;
import portfolio.PhotoSharingApp.entity.Member;
import portfolio.PhotoSharingApp.security.LoginUserDetails;
import portfolio.PhotoSharingApp.service.album.AlbumService;
import portfolio.PhotoSharingApp.service.group.GroupService;
import portfolio.PhotoSharingApp.service.member.MemberService;

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
			,RedirectAttributes redirectAttributes
			,@ModelAttribute("group")Group group
			) {
		
		/*自身がグループの管理者であるかを確認*/
		if (group.getAccountId() == user.getUserId()) {
			model.addAttribute("isAdmin",true);
		}
		
		boolean isActive = true;
	    model.addAttribute("isActiveHome", isActive);
	    
	    /*このグループの管理者名を取得*/
		String adminName = groupService.findByUsername(group.getId());
		model.addAttribute("adminName", adminName);
		
		/*このグループ利用者のテーブル情報とアカウント名を取得*/
		List<Member> memberList = memberService.findAllById(group.getId());
		model.addAttribute("memberList", memberList);
		
		/*このグループのアルバムIDとアルバム名を取得*/
		List<Album> albumList = albumService.findAllById(group.getId());
		model.addAttribute("albumList", albumList);
		
		/*このグループのメンバーの数を取得*/
		int countMembers = memberService.countMembersById(group.getId());
		model.addAttribute("countMembers", countMembers);
		
		/*このグループのアルバムの数を取得*/
		int countAlbums =  albumService.countAlbumsById(group.getId());
		model.addAttribute("countAlbums", countAlbums);
		
		return "group/dashboard";
	}
}