package VotingSystem.Model;

import java.awt.Component;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class ImageRenderer extends DefaultTableCellRenderer {
	    @Override
	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	        // Check if the value is an ImageIcon
	        if (value instanceof ImageIcon) {
	            setIcon((ImageIcon) value);
	            setText(""); // Clear the text
	        } else {
	            // Handle other data types or empty cells
	            setIcon(null);
	            setText(value != null ? value.toString() : "");
	        }

	        return this;
	    }

	    public static void setCustomImageRenderer(JTable table, int columnIndex) {
	        table.getColumnModel().getColumn(columnIndex).setCellRenderer(new DefaultTableCellRenderer() {
	            @Override
	            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	                if (value instanceof ImageIcon) {
	                    ImageIcon originalIcon = (ImageIcon) value;
	                    int targetWidth = table.getRowHeight(); // Set the width based on row height

	                    // Scale the image to the desired width while maintaining aspect ratio
	                    Image scaledImage = originalIcon.getImage().getScaledInstance(targetWidth, -1, Image.SCALE_SMOOTH);
	                    setIcon(new ImageIcon(scaledImage));
	                    setText(""); // Clear the text
	                } else {
	                    setIcon(null);
	                    setText(value != null ? value.toString() : "");
	                }
	                return this;
	            }
	        });
	    }

}

