package ts;

import application.DbContext;
import application.rdg.Character;
import application.rdg.CharacterFinder;
import application.rdg.Credits;
import application.rdg.Player;
import application.rdg.PlayerFinder;

import java.sql.Connection;
import java.sql.SQLException;

public class TradeCharacter {
    private Integer seller_id;
    private Player player_seller;
    private Integer buyer_id;
    private Player player_buyer;
    private Integer character_id;
    private application.rdg.Character character;
    private Credits seller_credits;
    private Credits buyer_credits;

    public Integer getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(Integer seller_id) {
        this.seller_id = seller_id;
    }

    public Player getPlayer_seller() {
        return player_seller;
    }

    public void setPlayer_seller(Player player_seller) {
        this.player_seller = player_seller;
    }

    public Integer getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(Integer buyer_id) {
        this.buyer_id = buyer_id;
    }

    public Player getPlayer_buyer() {
        return player_buyer;
    }

    public void setPlayer_buyer(Player player_buyer) {
        this.player_buyer = player_buyer;
    }

    public Integer getCharacter_id() {
        return character_id;
    }

    public void setCharacter_id(Integer character_id) {
        this.character_id = character_id;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public Credits getSeller_credits() {
        return seller_credits;
    }

    public void setSeller_credits(Credits seller_credits) {
        this.seller_credits = seller_credits;
    }

    public Credits getBuyer_credits() {
        return buyer_credits;
    }

    public void setBuyer_credits(Credits buyer_credits) {
        this.buyer_credits = buyer_credits;
    }

    /**
     * Function sets players, character. Draws funds from buyer and transfers percentage of it to seller. Than sets characters id_player id of buyer. Updates players and character. Inserts history of credits movement into database.
     */
    public void exchange() throws SQLException {
        player_seller = PlayerFinder.getInstance().findById(seller_id);
        player_buyer = PlayerFinder.getInstance().findById(buyer_id);
        character = CharacterFinder.getInstance().findById(character_id);
        seller_credits = new Credits();
        buyer_credits = new Credits();

        character.setPlayer_id(player_buyer.getId());

        player_seller.setCredits(player_seller.getCredits() + 350);
        player_buyer.setCredits(player_buyer.getCredits() - 400);

        seller_credits.setId_player(player_seller.getId());
        seller_credits.setType("+");
        seller_credits.setAmount(350);
        java.sql.Date sqlDate = java.sql.Date.valueOf(java.time.LocalDate.now());
        seller_credits.setDate(sqlDate);

        buyer_credits.setId_player(player_buyer.getId());
        buyer_credits.setType("-");
        buyer_credits.setAmount(400);
        buyer_credits.setDate(sqlDate);

        character.update();
        player_seller.update();
        player_buyer.update();
        seller_credits.insert();
        buyer_credits.insert();
    }

    /**
     * @throws SQLException
     * @throws Exception1 is thrown when one of tests is deemed wrong and instance is unusable or doesnt meet given conditions
     * function locks database with serializable(because will insert later). Than calls " exchange".
     */
    public void exchange_test() throws SQLException, Exception1 {
        Connection c = DbContext.getConnection();
        c.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        c.setAutoCommit(false);
        try{
            player_seller = PlayerFinder.getInstance().findById(seller_id);
            player_buyer = PlayerFinder.getInstance().findById(buyer_id);
            character = CharacterFinder.getInstance().findById(character_id);
            if (player_seller == null){
                throw new Exception1("There is no player with this ID");
            }
            if (player_buyer == null){
                throw new Exception1("There is no player with this ID");
            }
            if(player_buyer.getCredits() < 400){
                throw new Exception1("Insufficient credit's amount on buyer account");
            }
            if(character == null){
                throw new Exception1("There is no character with this ID");
            }
            if(!character.getPlayer_id().equals(seller_id)){
                throw new Exception1("Seller does not own this character." + seller_id + " idk " + character.getPlayer_id());
            }
            exchange();
            c.commit();
        } catch (Exception exception) {
            c.rollback();
            throw exception;
        } finally {
            c.setAutoCommit(true);
        }
    }
}
