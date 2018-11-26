package okcash.gamer;

import okcash.gamer.ok2048.Ok2048;

public enum GameType {
	OK2048("ok2048", Ok2048.class);

	GameType(String gameName, Class<? extends OkGame> clz) {
		this.gameName = gameName;
		this.clz = clz;
	}
	
	private String gameName;
	private Class<? extends OkGame> clz;

	public static OkGame getOkGame(String gameName) {
		for (GameType gameType : values()) {
			if (gameType.gameName.equalsIgnoreCase(gameName)) {
				try {
					return gameType.clz.newInstance();
					
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}
