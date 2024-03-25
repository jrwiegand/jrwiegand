package com.joshwiegand.abandon.managers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.badlogic.gdx.Gdx;

public class Save {

  public static Data data;

  public static void save() {
    try {
      ObjectOutputStream out = new ObjectOutputStream(
          new FileOutputStream("high_scores.sav")
      );
      out.writeObject(data);
      out.close();
    } catch (Exception e) {
      e.printStackTrace();
      Gdx.app.exit();
    }
  }

  public static void load() {
    try {
      if (!saveFileExists()) {
        init();
        return;
      }
      ObjectInputStream in = new ObjectInputStream(new FileInputStream("high_scores.sav"));
      data = (Data) in.readObject();
      in.close();
    } catch (Exception e) {
      e.printStackTrace();
      Gdx.app.exit();
    }
  }

  private static boolean saveFileExists() {
    return new File("high_scores.sav").exists();
  }

  private static void init() {
    data = new Data();
    data.init();
    save();
  }
}