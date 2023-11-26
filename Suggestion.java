/**
 * Represents a Suggestion submitted by a Camp Committee member for a Camp.
 * @author Tan Hui Ling
 * @version 1.0
 * @since 20/11/2023
 */
public class Suggestion {
    /**
     * The Camp Committee member who submitted this Suggestion.
     */
    private CampCommittee campCommittee;

    /**
     * The Camp this Suggestion is for.
     */
    private Camp camp;

    /**
     * The text of this Suggestion.
     */
    private String suggestionText;

    /**
     * Flag indicating whether this Suggestion was accepted by the Camp's Staff creator.
     * The default valuse is false indicating that the Suggestion is not currently accepted.
     */
    private boolean suggestionAccepted = false;

    /**
     * Flag indicating whether this Suggestion has been proccessed by the Camp's Staff creator.
     * Suggestion is considered processed if it has been accepted or rejected by the Staff.
     * The default valuse is false indicating that the Suggestion is not yet proccessed.
     */
    private boolean suggestionProcessed = false;

    
    /** 
     * Changes the Camp Committee member who submitted this Suggestion.
     * @param campCommittee The Camp Committee member who submitted the Suggestion.
     */
    public void setCampCommittee(CampCommittee campCommittee) {
        this.campCommittee = campCommittee;
    }

    
    /** 
     * Changes the Camp this Suggestion is for.
     * @param camp The Camp the Suggestion is for.
     */
    public void setCamp(Camp camp) {
        this.camp = camp;
    }

    
    /** 
     * Changes the Suggestion's text.
     * @param suggestionText The Suggestion's new text.
     */
    public void setSuggestionText(String suggestionText) {
        this.suggestionText = suggestionText;
    }

    /**
     * Indicates if the Suggestion has been accepted by the Staff in charge of the Camp.
     */
    public void setSuggestionAccepted() {
        this.suggestionAccepted = true;
    }

    
    /** 
     * Gets the Camp Committee member who submitted the Suggestion.
     * @return the Suggestion's Camp Committee.
     */
    public CampCommittee getCampCommittee() {
        return campCommittee;
    }

    
    /** 
     * Gets the Camp the Suggestion is for.
     * @return the Suggestion's Camp.
     */
    public Camp getCamp() {
        return camp;
    }

    /** 
     * Gets the Suggestion's text.
     * @return the Suggestion's text.
     */
    public String getSuggestionText() {
        return suggestionText;
    }

    /** 
     * Indicates the acceptance result of the Suggestion by the Staff.
     * true indicates Suggestion's acceptance.
     * false indicates Suggestion's rejection.
     * @return the Suggestion's acceptance status.
     */
    public boolean getSuggestionAccepted() {
        return suggestionAccepted;
    }

    /** 
     * Changes the processed status of the Suggestion from false to true.
     * Suggestion is considered processed if it has been accepted or rejected by the Staff.
     */
    public void suggProcessed(){
        this.suggestionProcessed = true;
    }

    /** 
     * Returns whether the Suggestion has been proccessed by the Staff.
     * Acceptance or rejection by the Staff reflects that it has been processed.
     * @return the Suggestion's procession status.
     */
    public boolean processStatus(){
        return this.suggestionProcessed;
    }
}
