import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.JComponent;

public class Song extends JComponent {

	LoopedList<PianoTile> notes;
	PianoTile firstNote;
	int songPos;
	int songSpeed;
	boolean noteWasMissed;
	int yNoteMissed;

	public Song() {
		// TODO Auto-generated constructor stub
		this.notes = new LoopedList<>();
		this.songSpeed = 10;
		this.noteWasMissed = false;
		tempMethod();
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				for (PianoTile tile : notes) {
					if (!tile.wasPressed()
							&& tile.xLocation * getWidth()/4 < e.getX() 
							&& (tile.xLocation + 1) * getWidth()/4 > e.getX()
							&& -tile.yLocation * getHeight()/4 + songPos < e.getY()
							&& -(tile.yLocation - 1) * getHeight()/4 + songPos > e.getY()) {
						tile.press();
					}
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

	}

	private void tempMethod() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 10; i++) {
			PianoTile pt = new PianoTile((int) (4 * Math.random()), i);
			// this.add(pt);
			notes.add(pt);
		}
		this.firstNote = notes.get(0);
	}

	public Song(File xmlFile) {
		// this.notes = this.generateNotes(xmlFile);
	}

	public void moveSongPos() {
		this.songPos += this.songSpeed;
	}

	public void incrementSongSpeed(int amount) {
		this.songSpeed += amount;
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		// super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.translate(0, songPos);
		for (PianoTile tile : notes) {
			g.setColor(tile.color);
			if (-tile.yLocation * this.getHeight() / 4 + this.songPos > 5*this.getHeight()/4 ) {
				tile.resetTo((int)(4*Math.random()), tile.yLocation + notes.size());
			}
			g2.fillRect(tile.xLocation * this.getWidth() / 4, -tile.yLocation * this.getHeight() / 4,
					this.getWidth() / 4, this.getHeight() / 4);
			
		}

	}
	
	public void checkForMissed() {
		for (PianoTile tile : notes) {
			if (-tile.yLocation * this.getHeight()/4 + this.songPos > this.getHeight()
					&& !tile.pressed) {
				this.noteWasMissed = true;
				tile.color = Color.RED;
				this.yNoteMissed = tile.yLocation;
				tile.noteClip.start();
			}
		}
	}
	
	public boolean missed() {
		return this.noteWasMissed;
	}
	
	public boolean rollingback() {
		this.songPos -= 20;
		return -this.yNoteMissed * this.getHeight()/4 + this.songPos >= 3*getHeight()/4;

	}

}
