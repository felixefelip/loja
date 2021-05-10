package com.spring.loja.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.loja.model.Produto;
import com.spring.loja.model.Role;
import com.spring.loja.model.Usuario;
import com.spring.loja.service.ProdutoService;
import com.spring.loja.service.RoleService;
import com.spring.loja.service.UsuarioService;

@Controller
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	RoleService roleService;

	@Autowired
	ProdutoService produtoService;

	Usuario usuarioLogado;

	@Autowired
	Usuario usuarioPerfil;

	@RequestMapping(value = "/cadastro", method = RequestMethod.POST)
	public String cadastrarUsuario(@Valid Usuario usuario, BindingResult result,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("mensagem", "Verifique se os campos obrigatórios foram preenchidos!");
			return "redirect:/";
		}

		Role role = roleService.findByRole("User");
		usuario.setListaRoles(Arrays.asList(role));
		usuario.setUsername(usuario.getEmail());

		usuarioService.save(usuario);
		return "redirect:/";
	}

	@RequestMapping(value = "/cadastro", method = RequestMethod.GET)
	public String getCadastroPage() {
		return "cadastro";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void getUser() {

	}

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public ModelAndView getPosts() {
		ModelAndView mv = new ModelAndView("produtos");
		List<Produto> produtos = produtoService.findAll();

		for (Produto produto : produtos) {
			System.out.println("imagem: " + produto.getImagem());
		}

		usuarioLogado = usuarioService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

		mv.addObject("produtos", produtos);
		mv.addObject("usuarioLogado", usuarioLogado);
		return mv;
	}

	@RequestMapping(value = "/newproduto", method = RequestMethod.GET)
	public String getProdutoForm() {
		return "produtoForm";
	}

	@RequestMapping(value = "/newproduto", method = RequestMethod.POST)
	public String salvarProduto(@Valid Produto produto, BindingResult result, RedirectAttributes redirectAttributes,
			@RequestParam("fileImage") MultipartFile multipartFile) throws IOException {
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("mensagem", "Verifique se os campos obrigatórios foram preenchidos!");
			return "redirect:/newpost";
		}

		usuarioLogado = usuarioService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		produto.setImagem(fileName);

		produto.setCadastrador(usuarioLogado);
		produto.setData(LocalDate.now());
		Produto savedProduto = produtoService.save(produto);

		String uploadDir = "src/main/resources/static/images";

		Path uploadPath = Paths.get(uploadDir);

		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}

		try (InputStream inputStream = multipartFile.getInputStream()) {
			Path filePath = uploadPath.resolve(fileName);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw new IOException("Could not save uploaded file: " + fileName);
		}
		return "redirect:/";
	}

	/*
	 * @RequestMapping(value = "/perfil/{username}", method = RequestMethod.GET)
	 * public ModelAndView getPerfil(@PathVariable("username") String username) {
	 * ModelAndView mv = new ModelAndView("perfilUsuario");
	 * 
	 * usuarioPerfil = usuarioService.findByUsername(username);
	 * 
	 * //usuarioLogado = (Usuario) SecurityContextHolder.getContext() //
	 * .getAuthentication().getPrincipal();
	 * 
	 * usuarioLogado =
	 * usuarioService.findByUsername(SecurityContextHolder.getContext()
	 * .getAuthentication().getName());
	 * 
	 * System.out.println("Nome do usuário logado: " + usuarioLogado.getNome());
	 * 
	 * System.out.println("Contém usuário logado: " +
	 * usuarioPerfil.getListaSeguidores());
	 * 
	 * List<Postagem> postsByUsuarioPerfil =
	 * postagemService.findAllByAutor(usuarioPerfil);
	 * 
	 * mv.addObject("postsByUsuarioPerfil", postsByUsuarioPerfil);
	 * mv.addObject("usuarioPerfil", usuarioPerfil); mv.addObject("usuarioLogado",
	 * usuarioLogado); return mv; }
	 * 
	 * @RequestMapping(value = "/meuPerfil") public String getMeuPerfil() {
	 * 
	 * return "redirect:/perfil/" +
	 * SecurityContextHolder.getContext().getAuthentication().getName(); }
	 * 
	 */
}
