package edu.wofford.machiwoco;

import java.util.ArrayList;

public class InputObserver extends Observer{

    public InputObserver(InputSubject inputSubject){
        this.inputSubject = inputSubject;
        this.inputSubject.attach(this);
    }

    @Override
    public void update() {
    //    handleInput()
    }
}
