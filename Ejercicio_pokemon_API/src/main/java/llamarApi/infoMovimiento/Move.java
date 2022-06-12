package llamarApi.infoMovimiento;

import java.util.ArrayList;

public class Move {

    private ArrayList<Pokemon> learned_by_pokemon;

    public ArrayList<Pokemon> getLearned_by_pokemon() {
        return learned_by_pokemon;
    }

    public void setLearned_by_pokemon(ArrayList<Pokemon> learned_by_pokemon) {
        this.learned_by_pokemon = learned_by_pokemon;
    }
}
