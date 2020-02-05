package com.brama.chess;

import com.brama.chess.core.board.StandardBoard;
import com.brama.chess.core.renderer.TextualRenderer;
import com.whitehatgaming.UserInput;
import com.whitehatgaming.UserInputFile;

import java.io.IOException;

public class GameRunner {

   public static void main(String... args) throws IOException {

      if (args.length < 1) {
         System.out.println("Please provide path to text file with moves as program arguments.");
         return;
      }

      System.out.println(String.format("Opening '%s'...", args[0]));
      UserInput userInput = new UserInputFile(args[0]);

      Game game = new Game(new TextualRenderer(), new StandardBoard());

      System.out.println("Running the game...");
      for (int[] move = userInput.nextMove(); move != null; move = userInput.nextMove()) {
         game.move(move);
         game.render();
      }
   }
}

