package level5;

import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.Event;
import net.java.games.input.EventQueue;

public final class GamepadHandler {

    private Controller gamepad = null;
    private boolean isAButtonPressed = false,
            isUpButtonPressed = false,
            isLeftButtonPressed = false,
            isRightButtonPressed = false,
            isR2ButtonPressed = false;
    private final float CENTER = Component.POV.CENTER,
            UP = Component.POV.UP,
            LEFT = Component.POV.LEFT,
            RIGHT = Component.POV.RIGHT,
            UP_LEFT = Component.POV.UP_LEFT,
            UP_RIGHT = Component.POV.UP_RIGHT,
            DOWN_LEFT = Component.POV.DOWN_LEFT,
            DOWN_RIGHT = Component.POV.DOWN_RIGHT;
    private float joystickX, prevJoystickX, joystickY, prevJoystickY;
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
        // System.out.println(component.getName());
        // System.out.println(component.getPollData());
        Component.Identifier identifier = component.getIdentifier();
        float value = event.getValue();
        if (identifier == Component.Identifier.Axis.POV) {
            this.handleDPad(value);
        } else if (identifier == Component.Identifier.Axis.Z || identifier == Component.Identifier.Axis.RZ) {
            this.handleJoystick(identifier, value);
        } else {
            this.handleButton(identifier, value);
        }
    }

    private void handleDPad(float value) {
        if (value == this.CENTER) {
            this.setToCenter();
        } else {
            this.isUpButtonPressed = (value == this.UP || value == this.UP_LEFT || value == this.UP_RIGHT);
            this.isLeftButtonPressed = (value == this.LEFT || value == this.UP_LEFT || value == this.DOWN_LEFT);
            this.isRightButtonPressed = (value == this.RIGHT || value == this.UP_RIGHT || value == this.DOWN_RIGHT);
        }
    }

    private void handleJoystick(Component.Identifier identifier, float value) {
        if (identifier == Component.Identifier.Axis.Z) {
            this.joystickX = value;
        } else if (identifier == Component.Identifier.Axis.RZ) {
            this.joystickY = value;
        }
    }

    private void handleButton(Component.Identifier identifier, float value) {
        boolean isPressed = (value == 1.0f);
        if (identifier == Component.Identifier.Button._1) {
            this.isAButtonPressed = isPressed;
        } else if (identifier == Component.Identifier.Button._7) {
            this.isR2ButtonPressed = isPressed;
        }
    }

    public float getJoystickX() {
        return this.joystickX;
    }

    public float getJoystickY() {
        return this.joystickY;
    }

    public void setToCenter() {
        this.isUpButtonPressed = false;
        this.isLeftButtonPressed = false;
        this.isRightButtonPressed = false;
    }

    public void updatePrevJoystickPositions() {
        this.prevJoystickX = this.joystickX;
        this.prevJoystickY = this.joystickY;
    }

    public boolean isAButtonPressed() {
        return this.isConnected() && this.isAButtonPressed;
    }

    public boolean isUpButtonPressed() {
        return this.isConnected() && this.isUpButtonPressed;
    }

    public boolean isLeftButtonPressed() {
        return this.isConnected() && this.isLeftButtonPressed;
    }

    public boolean isRightButtonPressed() {
        return this.isConnected() && this.isRightButtonPressed;
    }

    public boolean isR2ButtonPressed() {
        return this.isConnected() && this.isR2ButtonPressed;
    }

    public boolean isRightJoystickMoved() {
        return this.joystickX != this.prevJoystickX || this.joystickY != this.prevJoystickY;
    }
}
