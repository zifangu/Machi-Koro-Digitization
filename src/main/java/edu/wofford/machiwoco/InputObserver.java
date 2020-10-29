package edu.wofford.machiwoco;

import java.util.ArrayList;

public class InputObserver extends Observer{

    /**
     * A constructor that creates a InputSubject instance and adds itself to InputSubject's list of observers.
     * @param inputSubject a inputSubject instance that will be used to track/notify after inputs.
     */
    public InputObserver(InputSubject inputSubject){
        this.inputSubject = inputSubject;
        this.inputSubject.attach(this);
    }

    /**
     * Update after input has been observed
     */
    @Override
    public void update() {
    //    handleInput()
    }
}
