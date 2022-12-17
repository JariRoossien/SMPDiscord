package nl.dizmizzer.discord.commands;

public interface DCommandInput {

    String getInputName();

    boolean isRequired();

    static DCommandInput getNew(String inputName, boolean required) {
        return new DCommandInput() {
            @Override
            public String getInputName() {
                return inputName;
            }

            @Override
            public boolean isRequired() {
                return required;
            }
        };
    }
}
