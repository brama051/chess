package com.brama.chess.reader;

import com.whitehatgaming.UserInput;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

public class ResourcesFileReader implements UserInput {

   private final LineNumberReader reader;

   public ResourcesFileReader(String resourceFileName) throws FileNotFoundException {

      FileReader fr = new FileReader(new File(
            getClass().getClassLoader().getResource(resourceFileName).getFile()
      ));
      this.reader = new LineNumberReader(fr);
   }

   public int[] nextMove() throws IOException {

      String line = this.reader.readLine();
      if (line != null) {
         int[] move = new int[]{line.charAt(0) - 97, 56 - line.charAt(1), line.charAt(2) - 97,
               56 - line.charAt(3)};
         return move;
      } else {
         return null;
      }
   }

}
