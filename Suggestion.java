public class Suggestion {
    private CampCommittee campCommittee;
    private Camp camp;
    private String suggestionText;
    private boolean suggestionAccepted;

    public void setCampCommittee(CampCommittee campCommittee) {
        this.campCommittee = campCommittee;
    }

    public void setCamp(Camp camp) {
        this.camp = camp;
    }

    public void setSuggestionText(String suggestionText) {
        this.suggestionText = suggestionText;
    }

    public void setSuggestionAccepted(boolean suggestionAccepted) {
        this.suggestionAccepted = suggestionAccepted;
    }

    public CampCommittee getCampCommittee() {
        return campCommittee;
    }

    public Camp getCamp() {
        return camp;
    }

    public String getSuggestionText() {
        return suggestionText;
    }

    public boolean getSuggestionAccepted() {
        return suggestionAccepted;
    }
}
