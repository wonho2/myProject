package kr.spring.gg15.dto;

/*랭크정보, 랭크 승패, 랭크와 티어 표시. */
public class LeagueEntryDTO {
	private String queueType;
	private String summonerId;
	private String summonerName; 
	private int wins;
	private int losses;
	private String leagueId;
	private String rank;
	private String tier;
	private int leaguePoints;
	private boolean hotStreak;
	private boolean veteran;
	private boolean freshBlood;
	private boolean inactive;
	private MiniSeriesDTO miniSeriesDTO;
	
	public LeagueEntryDTO() {
		
	}

	public LeagueEntryDTO(String queueType,  int wins, int losses, String leagueId, String rank,
			String tier, int leaguePoints, String summonerId, String summonerName, boolean hotStreak,
			boolean veteran, boolean freshBlood, boolean inactive, MiniSeriesDTO miniSeriesDTO) {
		this.queueType = queueType;
		this.wins = wins;
		this.losses = losses;
		this.leagueId = leagueId;
		this.rank = rank;
		this.tier = tier;
		this.leaguePoints = leaguePoints;
		this.summonerId = summonerId;
		this.summonerName = summonerName;
		this.miniSeriesDTO = miniSeriesDTO;
		this.freshBlood = freshBlood;
		this.inactive = inactive;
		this.veteran = veteran;
		this.hotStreak = hotStreak;
	}
	
	public String getSummonerId() {
		return summonerId;
	}

	public void setSummonerId(String summonerId) {
		this.summonerId = summonerId;
	}

	public String getSummonerName() {
		return summonerName;
	}

	public void setSummonerName(String summonerName) {
		this.summonerName = summonerName;
	}

	public boolean isHotStreak() {
		return hotStreak;
	}

	public void setHotStreak(boolean hotStreak) {
		this.hotStreak = hotStreak;
	}

	public boolean isVeteran() {
		return veteran;
	}

	public void setVeteran(boolean veteran) {
		this.veteran = veteran;
	}

	public boolean isFreshBlood() {
		return freshBlood;
	}

	public void setFreshBlood(boolean freshBlood) {
		this.freshBlood = freshBlood;
	}

	public boolean isInactive() {
		return inactive;
	}

	public void setInactive(boolean inactive) {
		this.inactive = inactive;
	}

	public MiniSeriesDTO getMiniSeriesDTO() {
		return miniSeriesDTO;
	}

	public void setMiniSeriesDTO(MiniSeriesDTO miniSeriesDTO) {
		this.miniSeriesDTO = miniSeriesDTO;
	}
	public String getQueueType() {
		return queueType;
	}

	public void setQueueType(String queueType) {
		this.queueType = queueType;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getLosses() {
		return losses;
	}

	public void setLosses(int losses) {
		this.losses = losses;
	}

	public String getLeagueId() {
		return leagueId;
	}

	public void setLeagueId(String leagueId) {
		this.leagueId = leagueId;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getTier() {
		return tier;
	}

	public void setTier(String tier) {
		this.tier = tier;
	}

	public int getLeaguePoints() {
		return leaguePoints;
	}

	public void setLeaguePoints(int leaguePoints) {
		this.leaguePoints = leaguePoints;
	}

	@Override
	public String toString() {
		return "LeagueEntrydto [queueType=" + queueType + ", wins=" + wins
				+ ", losses=" + losses + ", leagueId=" + leagueId + ", rank=" + rank + ", tier=" + tier
				+ ", leaguePoints=" + leaguePoints + "]";
	}
	
	
	
	
}