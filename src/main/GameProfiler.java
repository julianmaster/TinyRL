package main;

public class GameProfiler {

	private long scriptDuration = 0l;
	private long renderDuration = 0l;
	private long sleepDuration = 0l;
	
	public long frameStartScriptDuration = 0l;
	public long frameStartRenderDuration = 0l;
	public long frameStartSleepDuration = 0l;
	
	public GameProfiler() {
	}
	
	public void startScript() {
		frameStartScriptDuration = System.nanoTime();
	}
	
	public void endScript() {
		scriptDuration = System.nanoTime() - frameStartScriptDuration;
	}
	
	public void startRender() {
		frameStartRenderDuration = System.nanoTime();
	}
	
	public void endRender() {
		renderDuration = System.nanoTime() - frameStartRenderDuration;
	}
	
	public void startSleep() {
		frameStartSleepDuration = System.nanoTime();
	}
	
	public void endSleep() {
		sleepDuration = System.nanoTime() - frameStartSleepDuration;
	}
	
	public void render() {
		System.out.println("ScriptDuration : "+String.format("%8s", scriptDuration)+" ns - RenderDuration : "+String.format("%8s", renderDuration)+" ns - SleepDuration : "+String.format("%8s", sleepDuration)+" ns");
	}
}
