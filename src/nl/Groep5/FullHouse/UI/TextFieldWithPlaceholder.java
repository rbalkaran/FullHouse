package nl.Groep5.FullHouse.UI;

import javax.swing.*;
import java.awt.*;

public class TextFieldWithPlaceholder extends JTextField {

    private String placeholder;

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);

        if(getText().isEmpty() && ! (FocusManager.getCurrentKeyboardFocusManager().getFocusOwner() == this)){
            final Graphics2D g2D = (Graphics2D) g;
            g2D.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g2D.setColor(getDisabledTextColor());
            g2D.drawString(placeholder, getInsets().left, g.getFontMetrics()
                    .getMaxAscent() + getInsets().top);
        }
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }
}
