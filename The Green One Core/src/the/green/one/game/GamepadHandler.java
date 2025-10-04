package the.green.one.game;

import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.Event;
import net.java.games.input.EventQueue;

public final class GamepadHandler {

    private Controller gamepad = null;
    private boolean isAButtonPressed = false,
            isXButtonPressed = false,
            isUpButtonPressed = false,
            isDownButtonPressed = false,
            isLeftButtonPressed = false,
            isRightButtonPressed = false,
            isStartButtonPressed = false;
    private final float CENTER = Component.POV.CENTER,
            UP = Component.POV.UP,
            DOWN = Component.POV.DOWN,
            LEFT = Component.POV.LEFT,
            RIGHT = Component.POV.RIGHT,
            UP_LEFT = Component.POV.UP_LEFT,
            UP_RIGHT = Component.POV.UP_RIGHT,
            DOWN_LEFT = Component.POV.DOWN_LEFT,
            DOWN_RIGHT = Component.POV.DOWN_RIGHT;
    public final boolean[] gamepadStatuses;

    public GamepadHandler() {
        this.gamepadStatuses = new boolean[2];
        this.connect();
    }

    private void connect() {
        Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
        for (Controller controller : controllers) {
            if (controller.getType() == Controller.Type.GAMEPAD) {
                this.gamepad = controller;
                return;
            }
        }
        this.gamepad = null;
    }

    private boolean isConnected() {
        return this.gamepad != null;
    }

    public boolean checkConnection() {
        if (this.gamepad == null) {
            return false;
        }
        boolean isStillConnected = this.gamepad.poll();
        this.gamepad = isStillConnected ? this.gamepad : null;
        return isStillConnected;
    }

    public boolean update() {
        boolean connected = this.checkConnection();
        if (connected) {
            EventQueue eventQueue = this.gamepad.getEventQueue();
            Event event = new Event();
            while (eventQueue.getNextEvent(event)) {
                this.handleEvent(event);
            }
        } else {
            this.connect();
        }
        return connected;
    }

    private void handleEvent(Event event) {
        Component component = event.getComponent();
        Component.Identifier identifier = component.getIdentifier();
        float value = event.getValue();
        if (identifier == Component.Identifier.Axis.POV) {
            this.handleDPad(value);
        } else {
            this.handleButton(identifier, value);
        }
    }

    private void handleButton(Component.Identifier identifier, float value) {
        boolean isPressed = (value == 1.0f);
        if (identifier == Component.Identifier.Button._0) {
            this.isXButtonPressed = isPressed;
        } else if (identifier == Component.Identifier.Button._1) {
            this.isAButtonPressed = isPressed;
        } else if (identifier == Component.Identifier.Button._9) {
            this.isStartButtonPressed = isPressed;
        }
    }

    private void handleDPad(float value) {
        if (value == this.CENTER) {
            this.setToCenter();
        } else {
            this.isUpButtonPressed = (value == this.UP || value == this.UP_LEFT || value == this.UP_RIGHT);
            this.isDownButtonPressed = (value == this.DOWN || value == this.DOWN_LEFT || value == this.DOWN_RIGHT);
            this.isLeftButtonPressed = (value == this.LEFT || value == this.UP_LEFT || value == this.DOWN_LEFT);
            this.isRightButtonPressed = (value == this.RIGHT || value == this.UP_RIGHT || value == this.DOWN_RIGHT);
        }
    }

    public void setToCenter() {
        this.isUpButtonPressed = false;
        this.isDownButtonPressed = false;
        this.isLeftButtonPressed = false;
        this.isRightButtonPressed = false;
    }

    public boolean isAButtonPressed() {
        return this.isConnected() && this.isAButtonPressed;
    }

    public boolean isXButtonPressed() {
        return this.isConnected() && this.isXButtonPressed;
    }

    public boolean isStartButtonPressed() {
        return this.isConnected() && this.isStartButtonPressed;
    }

    public boolean isUpButtonPressed() {
        return this.isConnected() && this.isUpButtonPressed;
    }

    public boolean isDownButtonPressed() {
        return this.isConnected() && this.isDownButtonPressed;
    }

    public boolean isLeftButtonPressed() {
        return this.isConnected() && this.isLeftButtonPressed;
    }

    public boolean isRightButtonPressed() {
        return this.isConnected() && this.isRightButtonPressed;
    }
}
