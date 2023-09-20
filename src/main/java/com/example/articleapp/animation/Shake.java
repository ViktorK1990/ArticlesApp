package com.example.articleapp.animation;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Shake {
    TranslateTransition tt;
    public Shake(Node node) {
        tt = new TranslateTransition(Duration.millis(50), node);
        tt.setFromX(0);
        tt.setByX(10);
        tt.setCycleCount(6);
        tt.setAutoReverse(true);
    }

    public void playAnimation(){
        tt.playFromStart();
    }
}
