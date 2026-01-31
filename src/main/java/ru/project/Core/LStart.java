package ru.project.Core;

import java.util.ArrayList;
import java.util.List;

public class LStart implements LogElement{

    /* input - список входов. В каждом элементе может быть от 0 до 2 входов. Пример:
		- Лог. эл. AND - два входа.
		- Лог. эл. NOT - один вход.
		- Элемент Button - не имеет входов.
	*/
    private List<LogElement> input = new ArrayList<>();
    private boolean state = true;

    // output -
    private LogElement output;

    @Override
    public List<LogElement> getInput() {
        return input;
    }

    @Override
    public LogElement getInputIndex(int index) {
        return input.get(index);
    }

    @Override
    public void setInput(List<LogElement> input) {
        this.input = input;
    }

    @Override
    public void setInputIndex(LogElement input) {
        this.input.add(input);
    }

    @Override
    public void setOutput(LogElement output) {
        this.output = output;
    }

    @Override
    public LogElement getOutput() {
        return output;
    }

    public boolean simulation(){
        return true;
    }

    @Override
    public boolean isState(){
        return state;
    }

}
