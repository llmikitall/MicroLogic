package ru.project.Core;

import java.util.List;

public class LAnd implements LogElement{

    /* input - список входов. В каждом элементе может быть от 0 до 2 входов. Пример:
		- Лог. эл. AND - два входа.
		- Лог. эл. NOT - один вход.
		- Элемент Button - не имеет входов.
	*/
    List<LogElement> input;

    // output -
    LogElement output;

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
        if (input.size() == 0)
            return false;
        boolean state = true;
        for (LogElement logElement : input)
            state = state && (logElement.simulation());

        return state;
    }

}
