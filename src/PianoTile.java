import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JComponent;

import jm.audio.Instrument;
import jm.music.data.Note;
import jm.music.data.Part;
import jm.music.data.Phrase;
import jm.music.data.Score;
import jm.util.AudioSampleInst;
import jm.util.Play;
import jm.util.Write;

public class PianoTile extends JComponent {
	
	int xLocation;
	int yLocation;
	boolean pressed;
	
	Color color = Color.BLACK;
	
	Clip noteClip;
	

	public PianoTile(int xLocation, int yLocation) {
		// TODO Auto-generated constructor stub
		this.xLocation = xLocation;
		this.yLocation = yLocation;
		this.pressed = false; 
	    try {
			noteClip = AudioSystem.getClip();
			noteClip.open(AudioSystem.getAudioInputStream(Main.class.getResource("/Cnote.wav")));
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//	    Note n = new Note(jm.constants.Pitches.A4, Note.DEFAULT_RHYTHM_VALUE);
//		Score score = new Score(new Part(new Phrase(n)));
//		Instrument sineWave = new SimpleSineInst(44100);
//		Write.au(score, "note.au" ,sineWave);
	}
	
	public void press() {
		this.pressed = true;
		this.color = Color.GRAY;
		noteClip.start();
//		new Thread( () -> {
//			Play.au("note.au");
//		}).start();
		//Play.audio(new Note(jm.constants.Pitches.A8, Note.DEFAULT_RHYTHM_VALUE), new AudioSampleInst("/Users/denniskats/eclipse-workspace/PianoTiles/src/Cnote.wav"));
	}
	
	public boolean wasPressed() {
		return this.pressed;
	}
	
	public void resetTo(int newX, int newY) {
		this.xLocation = newX;
		this.yLocation = newY;
		this.color = Color.BLACK;
		this.pressed = false;
		noteClip.setMicrosecondPosition(0);
	}
	

}
