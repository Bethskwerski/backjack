package com.lmig.gfc.blackjack.controllers;

import java.util.EmptyStackException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lmig.gfc.blackjack.models.BlackjackGame;

@Controller
public class BlackjackController {
	private BlackjackGame game;

	public BlackjackController() {
		game = new BlackjackGame();

	}

	@GetMapping("/")
	public ModelAndView defaultPage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("default");
		mv.addObject("bankroll", game.getBankRoll());
		mv.addObject("game", game);
		return mv;

	}

	@PostMapping("/play")
	public ModelAndView showStartScreen(double bet) {
		ModelAndView mv;
		mv = new ModelAndView();
		mv.setViewName("play");
		mv.addObject("allowStand", false);
		game.setBet(bet);
		try {
			game.startHand();
		} catch (EmptyStackException ese) {
			String errorMessage = "That is the end of the deck!!";
			mv.addObject("stringResult", errorMessage);
		}
		game.isHandOver();
		game.doesPlayerWin();
		game.doesHouseWin();
		mv.addObject("dealerCard", game.getDealer().getDealerHand().getCards());
		mv.addObject("playerCard", game.getPlayer().getPlayerHand().getCards());
		mv.addObject("playerHandPoints", game.getPlayer().getPlayerHand().getTotal());
		mv.addObject("dealerHandPoints", game.getDealer().getDealerHand().getTotal());
		game.goWrapUpHand();
		mv.addObject("playerWins", game.doesPlayerWin());
		mv.addObject("dealerWins", game.doesHouseWin());
		mv.addObject("handOver", game.isHandOver());
		mv.addObject("showCard", game.isHandOver());
		mv.addObject("newHand", game.isHandOver());
		mv.addObject("push", game.isPush());
		mv.addObject("playerBlackJack", game.isPlayerBlackjack());
		mv.addObject("dealerBlackJack", game.isDealerBlackjack());
		mv.addObject("canDouble", game.canDoubleDown());
		mv.addObject("canHit", game.canHit());
		return mv;

	}

	@PostMapping("/hitme")
	public ModelAndView showHitScreen() {
		ModelAndView mv;
		mv = new ModelAndView();
		mv.setViewName("play");
		mv.addObject("allowStand", false);	
		game.doHit();
		game.isHandOver();
		game.doesPlayerWin();
		game.doesHouseWin();
		mv.addObject("canDouble", game.canDoubleDown());
		mv.addObject("dealerCard", game.getDealer().getDealerHand().getCards());
		mv.addObject("playerCard", game.getPlayer().getPlayerHand().getCards());
		mv.addObject("playerHandPoints", game.getPlayer().getPlayerHand().getTotal());
		mv.addObject("dealerHandPoints", game.getDealer().getDealerHand().getTotal());
		game.goWrapUpHand();
		mv.addObject("showCard", game.isHandOver());
		mv.addObject("playerWins", game.doesPlayerWin());
		mv.addObject("dealerWins", game.doesHouseWin());
		mv.addObject("handOver", game.isHandOver());
		mv.addObject("playerBust", game.getPlayer().getPlayerHand().isBust() == true);
		mv.addObject("playerBlackJack", game.isPlayerBlackjack());
		mv.addObject("dealerBlackJack", game.isDealerBlackjack());
		mv.addObject("canHit", game.canHit());
		return mv;

	}
	
	@PostMapping("/doubleDown")
	public ModelAndView showDoubleDownScreen() {
		ModelAndView mv;
		mv = new ModelAndView();
		mv.setViewName("play");
		mv.addObject("allowStand", false);
		game.doDoubleDown();
		game.hitDealer();
		game.isHandOver();
		game.doesPlayerWin();
		game.doesHouseWin();
		mv.addObject("dealerCard", game.getDealer().getDealerHand().getCards());
		mv.addObject("playerCard", game.getPlayer().getPlayerHand().getCards());
		mv.addObject("playerHandPoints", game.getPlayer().getPlayerHand().getTotal());
		mv.addObject("dealerHandPoints", game.getDealer().getDealerHand().getTotal());
		game.goWrapUpHand();
		mv.addObject("showCard", game.isHandOver());
		mv.addObject("canDouble", game.canDoubleDown());
		mv.addObject("playerWins", game.doesPlayerWin());
		mv.addObject("dealerWins", game.doesHouseWin());
		mv.addObject("handOver", game.isHandOver());
		mv.addObject("playerBust", game.getPlayer().getPlayerHand().isBust() == true);
		mv.addObject("playerBlackJack", false);
		mv.addObject("dealerBlackJack", false);
		mv.addObject("canHit", false);
		return mv;

	}

	@PostMapping("/stand")
	public ModelAndView showStandScreen() {
		ModelAndView mv;
		mv = new ModelAndView();
		mv.setViewName("play");
		mv.addObject("allowStand", false);
		game.hitDealer();
		game.isHandOver();
		game.doesPlayerWin();
		game.doesHouseWin();
		mv.addObject("canDouble", game.canDoubleDown());
		mv.addObject("dealerCard", game.getDealer().getDealerHand().getCards());
		mv.addObject("playerCard", game.getPlayer().getPlayerHand().getCards());
		mv.addObject("playerHandPoints", game.getPlayer().getPlayerHand().getTotal());
		mv.addObject("dealerHandPoints", game.getDealer().getDealerHand().getTotal());
		game.goWrapUpHand();
		mv.addObject("playerWins", game.doesPlayerWin());
		mv.addObject("dealerWins", game.doesHouseWin());
		mv.addObject("showCard", game.isHandOver());
		mv.addObject("handOver", game.isHandOver());
		mv.addObject("playerBlackJack", game.isPlayerBlackjack());
		mv.addObject("dealerBlackJack", game.isDealerBlackjack());
		mv.addObject("canHit", game.canHit());
		return mv;

	}

	@PostMapping("/newHand")
	public ModelAndView showNewHandScreen() {
		ModelAndView mv;
		mv = new ModelAndView();
		mv.setViewName("default");
		mv.addObject("bankroll", game.getBankRoll());
		game.getPlayer().getPlayerHand().goStartNewHand();
		game.getDealer().getDealerHand().goStartNewHand();
		return mv;
	}
}
