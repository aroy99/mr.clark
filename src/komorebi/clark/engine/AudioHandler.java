package komorebi.clark.engine;


import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.util.ResourceLoader;

public class AudioHandler {
	
    private static Audio intro;
    private static Audio loop;
    private static Audio jump, slide, warn, death;
    private static boolean introStarted;
    private static boolean loopStarted;
    private static float resPos;
	
	public static void init(){
        try {
            intro = AudioLoader.getAudio("OGG", 
                    ResourceLoader.getResourceAsStream("res/music/intro.ogg"));
            loop = AudioLoader.getAudio("OGG", 
        	        ResourceLoader.getResourceAsStream("res/music/loop.ogg"));
        	
            jump = AudioLoader.getAudio("OGG", 
        			ResourceLoader.getResourceAsStream(
        											"res/music/sfx-jump.ogg"));
        	slide = AudioLoader.getAudio("OGG", 
        			ResourceLoader.getResourceAsStream(
        										"res/music/sfx-slide.ogg"));
        	warn = AudioLoader.getAudio("OGG", 
        			ResourceLoader.getResourceAsStream(
        										"res/music/sfx-warn.ogg"));
        	death = AudioLoader.getAudio("OGG", 
        			ResourceLoader.getResourceAsStream(
        										"res/music/sfx-death.ogg"));
        	
	        System.out.println("Initialized music (hopefully)");
		} catch (Exception e) {
			System.out.println("RIP MUSIC");
			e.printStackTrace();
		}

	}
	
	
	public static void play(String key){
	    if(key.equals("jump"))jump.playAsSoundEffect(1.0f, 1.0f, false);
	    else if(key.equals("slide"))slide.playAsSoundEffect(1.0f, 1.0f, false);
	    else if(key.equals("warn"))warn.playAsSoundEffect(1.0f, 1.0f, false);
	    else if(key.equals("death"))death.playAsSoundEffect(1.0f, 1.0f, false);
	    else System.out.println("Sound not played, \"" + key + 
														"\" is invalid...");
	}
	
    public static boolean playMusic(){
        if(!introStarted){
            intro.playAsSoundEffect(1.0f, 1.0f, false);
            intro.setPosition(resPos);
            introStarted = true;
            return false;
		}
		else if(intro.getPosition()>=11.9f && !loopStarted){
            loop.playAsSoundEffect(1.0f,1.0f,true);
            loopStarted = true;
            return true;
		}
	    return false;
    }


	public static void stop() {
		if(loopStarted){
			resPos = loop.getPosition();
			loop.stop();
			loopStarted = false;
		}
		else if(introStarted){
			resPos = intro.getPosition();
			intro.stop();
			introStarted = false;
		}
	}


	public static void resumeMusic() {
		if(!loopStarted){
			loop.playAsSoundEffect(1.0f,1.0f,true);
			loop.setPosition(resPos);
			loopStarted = true;
		}
	}
}
