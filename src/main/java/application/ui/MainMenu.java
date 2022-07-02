package application.ui;

import application.rdg.*;
import application.rdg.Character;
import ts.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class MainMenu extends Menu {
    @Override
    public void print() {
        System.out.println("♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕");
        System.out.println("|| 1. Players section            ||");
        System.out.println("|| ----------------------------- ||");
        System.out.println("|| 2. Characters section         ||");
        System.out.println("|| ----------------------------- ||");
        System.out.println("|| 3. Races section              ||");
        System.out.println("|| ----------------------------- ||");
        System.out.println("|| 4. Classes section            ||");
        System.out.println("|| ----------------------------- ||");
        System.out.println("|| 5. Advanced options           ||");
        System.out.println("|| ----------------------------- ||");
        System.out.println("|| 6. Exit                       ||");
        System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■");
    }

    @Override
    public void spracuj(String option) {
        try {
            switch (option) {
                case "1":   playerPanel(); break;
                case "2":   characterPanel(); break;
                case "3":   racePanel(); break;
                case "4":   classPanel(); break;
                case "5":   advancedPanel(); break;
                case "6":   exit(); break;
                default:    System.out.println("Unknown option"); break;
            }
        } catch(SQLException | IOException  /*| InterruptedException */e) {
            throw new RuntimeException(e);
        }
    }

    private void playerPanel() throws IOException {
        BufferedReader vstup = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕");
        System.out.println("|| 1. Show all players           ||");
        System.out.println("|| 2. Create a new player        ||");
        System.out.println("|| 3. Update player              ||");
        System.out.println("|| 4. Delete player              ||");
        System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■");
        String zo_vstupu = vstup.readLine();
        try{
            switch (zo_vstupu){
                case "1":   listAllPlayers(); break;
                case "2":   createAPlayer(); break;
                case "3":   updateAPlayer(); break;
                case "4":   deleteAPlayer(); break;
                default:    System.out.println("Unknown option"); break;
            }
        } catch (SQLException | Exception1 e){
            throw new RuntimeException(e);
        }
    }
    private void characterPanel() throws SQLException, IOException {
        BufferedReader vstup = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕");
        System.out.println("|| 1. Show all characters        ||");
        System.out.println("|| 2. Create a new character     ||");
        System.out.println("|| 3. Equip new item             ||");
        System.out.println("|| 4. Update character           ||");
        System.out.println("|| 5. Delete character           ||");
        System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■");
        String zo_vstupu = vstup.readLine();
        try{
            switch (zo_vstupu){
                case "1":   listAllCharacters(); break;
                case "2":   createACharacter(); break;
                case "3":   equipACharacter(); break;
                case "4":   updateACharacter(); break;
                case "5":   deleteACharacter(); break;
                default:    System.out.println("Unknown option"); break;
            }
        } catch (SQLException | Exception1 e){
            throw new RuntimeException(e);
        }
    }
    private void racePanel() throws IOException {
        BufferedReader vstup = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕");
        System.out.println("|| 1. Show all races            ||");
        System.out.println("|| 2. Create a new race         ||");
        System.out.println("|| 3. Add/Delete class of race  ||");
        System.out.println("|| 4. Delete race               ||");
        System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■");
        String zo_vstupu = vstup.readLine();
        try{
            switch (zo_vstupu){
                case "1":   listAllRaces(); break;
                case "2":   createARace(); break;
                case "3":   classToRace(); break;
                case "4":   deleteARace(); break;
                default:    System.out.println("Unknown option"); break;
            }
        } catch (SQLException | Exception1 e){
            throw new RuntimeException(e);
        }
    }
    private void classPanel() throws IOException {
        BufferedReader vstup = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕");
        System.out.println("|| 1. Show all classes          ||");
        System.out.println("|| 2. Create a new class        ||");
        System.out.println("|| 3. Show skills of class      ||");
        System.out.println("|| 4. Update class skills       ||");
        System.out.println("|| 5. Delete class              ||");
        System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■");
        String zo_vstupu = vstup.readLine();
        try{
            switch (zo_vstupu){
                case "1":   listAllClasses(); break;
                case "2":   createAClass(); break;
                case "3":   showSkills(); break;
                case "4":   updateSkills(); break;
                case "5":   deleteAClass(); break;
                default:    System.out.println("Unknown option"); break;
            }
        } catch (SQLException | Exception1 e){
            throw new RuntimeException(e);
        }
    }
    private void advancedPanel() throws IOException {
        BufferedReader vstup = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕");
        System.out.println("|| 1. ⚔⚔⚔ Battle ⚔⚔             ||");
        System.out.println("|| ----------------------------- ||");
        System.out.println("|| 2. Print players payments     ||");
        System.out.println("|| ----------------------------- ||");
        System.out.println("|| 3. Shop                       ||");
        System.out.println("|| 4. Rippers                    ||");
        System.out.println("|| 5. Metamorphosis              ||");
        System.out.println("|| 6. Bounty Hunter              ||");
        System.out.println("|| 7. Extent                     ||");
        System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■");
        String zo_vstupu = vstup.readLine();
        try{
            switch (zo_vstupu){
                case "1":   battle(); break;
                case "2":   showPayments(); break;
                case "3":   shop(); break;
                case "4":   jacks(); break;
                case "5":   metamorphosis(); break;
                case "6":   bountyhunter(); break;
                case "7":   extentstat(); break;
                default:    System.out.println("Unknown option"); break;
            }
        } catch (SQLException | Exception1 e){
            throw new RuntimeException(e);
        }
    }


    /**
     * Function calls finder to get List of all players and prints them out
     */
    private void listAllPlayers() throws SQLException{
        for(Player player : PlayerFinder.getInstance().findAll()){
            PlayerPrinter.getInstance().print(player);
        }
    }

    /**
     * Function to create new player. It takes username, name, email from user. Sets credits for every player and inserts into database new player. After that it prints it out for user to see.
     */
    private void createAPlayer() throws SQLException, IOException {
        BufferedReader vstup = new BufferedReader(new InputStreamReader(System.in));
        Player player = new Player();

        System.out.println("Enter username:");
        player.setUsername(vstup.readLine());
        System.out.println("Enter first name:");
        player.setFirst_name(vstup.readLine());
        System.out.println("Enter last name:");
        player.setLast_name(vstup.readLine());
        System.out.println("Enter email:");
        player.setEmail(vstup.readLine());
        player.setCredits(100);

        player.insert();

        System.out.println("New player has been successfully created");
        System.out.print("Player's id is: ");
        System.out.println(player.getId());
    }

    /**
     * Function to update information about player. Takes instances from user and than updates existing player.
     */
    private void updateAPlayer() throws SQLException, IOException, Exception1 {
        BufferedReader vstup = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter player ID: ");
        int player_id = Integer.parseInt(vstup.readLine());

        Player player = PlayerFinder.getInstance().findById(player_id);

        if(player == null) {
            throw new Exception1("No player with this ID");
        } else {
            PlayerPrinter.getInstance().print(player);

            System.out.println("Enter new username: ");
            player.setUsername(vstup.readLine());
            System.out.println("Enter new first name: ");
            player.setFirst_name(vstup.readLine());
            System.out.println("Enter new last name: ");
            player.setLast_name(vstup.readLine());
            System.out.println("Enter new email: ");
            player.setEmail(vstup.readLine());
            player.setCredits(player.getCredits());

            player.update();

            System.out.println("Player info was updated");
        }
    }

    /**
     * Function to delete existing player. Takes player id and deletes him from database(along with his characters ...)
     */
    private void deleteAPlayer() throws SQLException, IOException, Exception1 {
        BufferedReader vstup = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter player ID: ");
        int player_id = Integer.parseInt(vstup.readLine());

        Player player = PlayerFinder.getInstance().findById(player_id);

        if(player == null){
            throw new Exception1("No player with this ID");
        } else {
            player.delete();
            System.out.println("Player id: " + player_id + " was successfully deleted.");
        }
    }

    /**
     * Function to list all existing characters
     */
    private void listAllCharacters() throws SQLException{
        for(Character character : CharacterFinder.getInstance().findAll()){
            CharacterPrinter.getInstance().print(character);
        }
    }

    /**
     * Function to create new character. It takes data from user and controls if they exist in database or are allowed. At he end created new character and inserts him into database.
     */
    private void createACharacter() throws SQLException, IOException, Exception1 {
        BufferedReader vstup = new BufferedReader(new InputStreamReader(System.in));
        Character character = new Character();
        System.out.println("Enter player ID: ");
        int player_id = Integer.valueOf(vstup.readLine());
        Player player = PlayerFinder.getInstance().findById(player_id);
        if(player == null){
            throw new Exception1("No player with this ID");
        } else {
            character.setPlayer_id(player_id);
            System.out.println("Enter name: ");
            character.setName(vstup.readLine());
            System.out.println("Enter gender: ");
            character.setSex(vstup.readLine());

            System.out.println("Enter race: ");
            int race_id = Integer.valueOf(vstup.readLine());
            Race race = RaceFinder.getInstance().findById(race_id);
            if(race == null){
                throw new Exception1("There is no such race with this ID.");
            } else {
                character.setRace_id(race_id);
                System.out.println("Enter class: ");
                int class_id = Integer.valueOf(vstup.readLine());
                Klass klass = KlassFinder.getInstance().findById(class_id);
                if(klass == null){
                    throw new Exception1("There is no such class with this ID.");
                } else {
                    PossibleClass possibleClass = PossibleClassFinder.getInstance().find(race_id, class_id);
                    if(possibleClass == null){
                        System.out.println("Race " + race_id + " is unable to play as class " + class_id + ".");
                    } else {
                        character.setClass_id(class_id);
                        character.setExp(0);
                        character.setLevel(1);
                        character.setMax_hp(klass.getClass_hp());
                        character.setCurrent_hp(klass.getClass_hp());
                        character.setPower(klass.getClass_power());
                        character.setDefense(klass.getClass_defense());
                        character.setIs_alive(true);
                        System.out.println("Enter head: ");
                        int head_id = Integer.valueOf(vstup.readLine());
                        Head head = HeadFinder.getInstance().findById(head_id);
                        if(head == null){
                            throw new Exception1("There is no such head with this ID.");
                        } else {
                            character.setHead(head_id);
                            System.out.println("Enter body: ");
                            int body_id = Integer.valueOf(vstup.readLine());
                            Body body = BodyFinder.getInstance().findById(body_id);
                            if(body == null){
                                throw new Exception1("There is no such body with this ID.");
                            } else {
                                character.setBody(body_id);
                                System.out.println("Enter hair: ");
                                int hair_id = Integer.valueOf(vstup.readLine());
                                Hair hair = HairFinder.getInstance().findById(hair_id);
                                if(hair == null) {
                                    throw new Exception1("There is no such hair with this ID.");
                                } else {
                                    character.setHair(hair_id);
                                    System.out.println("Enter shirt: ");
                                    int shirt_id = Integer.valueOf(vstup.readLine());
                                    Shirt shirt = ShirtFinder.getInstance().findById(shirt_id);
                                    if(shirt == null){
                                        throw new Exception1("There is no such shirt with this ID.");
                                    } else {
                                        character.setShirt(shirt_id);
                                        System.out.println("Enter pants: ");
                                        int pants_id = Integer.valueOf(vstup.readLine());
                                        Pants pants = PantsFinder.getInstance().findById(pants_id);
                                        if(pants == null){
                                            throw new Exception1("There is no such pants with this ID.");
                                        } else {
                                            character.setPants(pants_id);
                                            Item item_weapon = ItemFinder.getInstance().randomWeapon();
                                            Item item_armor = ItemFinder.getInstance().randomArmor();
                                            Equip equip = new Equip();
                                            equip.setWeapon(item_weapon.getId());
                                            equip.setArmor(item_armor.getId());
                                            equip.insert();
                                            character.setEquipment(equip.getId());

                                            character.insert();
                                            System.out.print("New character was created his ID is: ");
                                            System.out.println(character.getId());
                                            CharacterPrinter.getInstance().print(character);
                                        }
                                    }
                                }
                            }
                        }

                    }
                }
            }
        }
    }
    private void equipACharacter() throws IOException {
        BufferedReader vstup = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕");
        System.out.println("||           Options             ||");
        System.out.println("||===============================||");
        System.out.println("|| 1. Equip a weapon \uD83D\uDDE1          ||");
        System.out.println("|| 2. Equip an armor  ⛨         ||");
        System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■");
        String zo_vstupu = vstup.readLine();
        try{
            switch (zo_vstupu){
                case "1":   equipAWeapon(); break;
                case "2":   equipAnArmor(); break;
                default:    System.out.println("Unknown option"); break;
            }
        } catch (SQLException | Exception1 e){
            throw new RuntimeException(e);
        }
    }

    /**
     * Function gives user list of weapons he's currently able to equip and than after checking if inputed value is legit equips new weapon.
     */
    private void equipAWeapon() throws SQLException, IOException, Exception1 {
        BufferedReader vstup = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter character ID: ");
        int character_id = Integer.parseInt(vstup.readLine());
        Character character = CharacterFinder.getInstance().findById(character_id);
        if(character == null){
            throw new Exception1("There is no character with such ID.");
        } else {
            System.out.println("You can choose from those weapons: ");
            for (Item item : ItemFinder.getInstance().findAllWeapons()){
                ItemPrinter.getInstance().print(item);
            }
            System.out.println("Enter weapon ID: ");
            int weapon_id = Integer.parseInt(vstup.readLine());
            Item i = ItemFinder.getInstance().findById(weapon_id);
            if(i == null){
                throw new Exception1("Weapon with this ID does not exist.");
            } else {
                Equip equip = EquipFinder.getInstance().findById(character_id);
                equip.setWeapon(weapon_id);
                equip.update();
                System.out.println("You equipped new weapon.");
            }
        }
    }

    /**
     * Function gives user list of armors he's currently able to equip and than after checking if inputted value is legit equips new armor.
     */
    private void equipAnArmor() throws SQLException, IOException, Exception1 {
        BufferedReader vstup = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter character ID: ");
        int character_id = Integer.parseInt(vstup.readLine());
        Character character = CharacterFinder.getInstance().findById(character_id);
        if(character == null){
            throw new Exception1("There is no character with such ID.");
        } else {
            System.out.println("You can choose from those armors: ");
            for (Item item : ItemFinder.getInstance().findAllArmors()){
                ItemPrinter.getInstance().print(item);
            }
            System.out.println("Enter armor ID: ");
            int armor_id = Integer.parseInt(vstup.readLine());
            Item i = ItemFinder.getInstance().findById(armor_id);
            if(i == null){
                throw new Exception1("Armor with this ID does not exist.");
            } else {
                Equip equip = EquipFinder.getInstance().findById(character_id);
                equip.setArmor(armor_id);
                equip.update();
                System.out.println("You equipped new armor.");
            }
        }
    }

    private void updateACharacter() throws IOException {
        BufferedReader vstup = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕");
        System.out.println("||           Options             ||");
        System.out.println("||===============================||");
        System.out.println("|| 1. Level up your character    ||");
        System.out.println("|| 2. Level down your character  ||");
        System.out.println("|| 3. Heal your character        ||");
        System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■");
        String zo_vstupu = vstup.readLine();
        try{
            switch (zo_vstupu){
                case "1":   levelUpYourCharacter(); break;
                case "2":   levelDownYourCharacter(); break;
                case "3":   healYourCharacter(); break;
                default:    System.out.println("Unknown option"); break;
            }
        } catch (SQLException | Exception1 e){
            throw new RuntimeException(e);
        }
    }

    /**
     * Function takes from user characted id and new level after that calls "levelUp" which changes characters stats according to proper leveling and set exp to bare minimum.
     */
    private void levelUpYourCharacter() throws SQLException, IOException, Exception1 {
        BufferedReader vstup = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter character ID: ");
        int character_id = Integer.parseInt(vstup.readLine());
        Character character = CharacterFinder.getInstance().findById(character_id);
        if(character == null){
            throw new Exception1("There is no character with such ID.");
        } else {
            System.out.println("Enter new level: ");
            int new_lvl = Integer.parseInt(vstup.readLine());
            Levels l = LevelsFinder.getInstance().findById(new_lvl);
            if(l == null){
                throw new Exception1("There is no such level in this game.");
            } else {
                if(new_lvl == character.getLevel()){
                    System.out.println("You are already level " + new_lvl + ".");
                } else {

                    for(int i = character.getLevel(); i <= new_lvl; i++){
                        LevelUp levelUp = new LevelUp();
                        levelUp.setCharacter(character);
                        levelUp.setLevels(LevelsFinder.getInstance().findById(i));
                        levelUp.setFake(true);
                        levelUp.setLevel(i);

                        levelUp.levelUp();
                    }
                    System.out.println("Now you are level " + new_lvl + ".");
                    CharacterPrinter.getInstance().print(character);
                }
            }
        }
    }

    /**
     * Function is reverse of "levelUpYourCharacter"
     */
    private void levelDownYourCharacter() throws SQLException, IOException, Exception1 {
        BufferedReader vstup = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter character ID: ");
        int character_id = Integer.parseInt(vstup.readLine());
        Character character = CharacterFinder.getInstance().findById(character_id);
        if(character == null){
            throw new Exception1("There is no character with such ID.");
        } else {
            System.out.println("Enter new level: ");
            int new_lvl = Integer.parseInt(vstup.readLine());
            Levels l = LevelsFinder.getInstance().findById(new_lvl);
            if(l == null){
                throw new Exception1("There is no such level in this game.");
            } else {
                if(new_lvl == character.getLevel()){
                    System.out.println("You are already level " + new_lvl + ".");
                } else {
                    for(int i = character.getLevel(); i > new_lvl; i--){
                        System.out.println("idk");
                        LevelUp levelUp = new LevelUp();
                        levelUp.setCharacter(character);
                        levelUp.setLevels(LevelsFinder.getInstance().findById(i));
                        levelUp.setFake(true);
                        levelUp.setLevel(i);

                        levelUp.levelDown();
                    }
                    System.out.println("Now you are level " + new_lvl + ".");
                    CharacterPrinter.getInstance().print(character);
                }
            }
        }
    }

    /**
     * Function to heal fully your character or revive it if its dead.
     */
    private void healYourCharacter() throws IOException, SQLException, Exception1 {
        BufferedReader vstup = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter character ID: ");
        int character_id = Integer.parseInt(vstup.readLine());
        Character character = CharacterFinder.getInstance().findById(character_id);
        if(character == null){
            throw new Exception1("There is no character with such ID.");
        } else {
            Heal heal = new Heal();
            if(character.isIs_alive()){
                heal.setCharacter(character);
                heal.setAlive(true);
                heal.healCharacter();
                System.out.println("Your character was healed.");
                CharacterPrinter.getInstance().print(character);
            } else {
                heal.setCharacter(character);
                heal.setAlive(false);
                heal.healCharacter();
                System.out.println("Your character was revived.");
                CharacterPrinter.getInstance().print(character);
            }
        }
    }

    /**
     * Function takes character id, checks its validity and than deletes character from database.
     */
    private void deleteACharacter() throws SQLException, IOException, Exception1 {
        BufferedReader vstup = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter character ID: ");
        int character_id = Integer.parseInt(vstup.readLine());
        Character character = CharacterFinder.getInstance().findById(character_id);
        if(character == null){
            throw new Exception1("There is no character with such ID.");
        } else {
            String name = character.getName();
            Equip equip = EquipFinder.getInstance().findById(character.getEquipment());
            character.delete();
            equip.delete();
            System.out.println("Character " + name + " was successfully deleted.");
        }
    }

    /**
     * Function to list all existing races.
     */
    private void listAllRaces() throws SQLException{
        for(Race race : RaceFinder.getInstance().findAll()){
            RacePrinter.getInstance().print(race);
        }
    }

    /**
     * Function allows you to create new race.
     */
    private void createARace() throws SQLException, IOException {
        BufferedReader vstup = new BufferedReader(new InputStreamReader(System.in));
        Race race = new Race();

        System.out.println("Enter name: ");
        race.setName(vstup.readLine());
        System.out.println("Enter lore: ");
        race.setLore(vstup.readLine());

        race.insert();

        System.out.println("New race has been successfully created.");
        System.out.print("Race id is: ");
        System.out.println(race.getId());
    }
    private void classToRace() throws SQLException, IOException {
        BufferedReader vstup = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕");
        System.out.println("||           Options             ||");
        System.out.println("||===============================||");
        System.out.println("|| 1. Add Class to Race          ||");// OK _ (vyrob na prvkovu aby si vyuzil goods a market))
        System.out.println("|| 2. Delete Class from Race     ||");
        System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■");
        String zo_vstupu = vstup.readLine();
        try{
            switch (zo_vstupu){
                case "1":   addClassToRace(); break;
                case "2":   deleteClassFromRace(); break;
                default:    System.out.println("Unknown option"); break;
            }
        } catch (SQLException | Exception1 e){
            throw new RuntimeException(e);
        }
    }

    /**
     * Function adds class as playable for race.
     */
    private void addClassToRace() throws SQLException, IOException, Exception1 {
        BufferedReader vstup = new BufferedReader(new InputStreamReader(System.in));
        PossibleClass possibleClass = new PossibleClass();
        System.out.println("Enter race ID: ");
        int race_id = Integer.parseInt(vstup.readLine());
        Race race = RaceFinder.getInstance().findById(race_id);
        if(race == null){
            throw new Exception1("There is no such race with this ID");
        } else {
            System.out.println("Enter class ID: ");
            int class_id = Integer.parseInt(vstup.readLine());
            Klass klass = KlassFinder.getInstance().findById(class_id);
            if(klass == null){
                throw new Exception1("There is no such class with this ID");
            } else {
                PossibleClass pom = PossibleClassFinder.getInstance().find(race_id, class_id);
                if(pom == null){
                    possibleClass.setRace_id(race_id);
                    possibleClass.setClass_id(class_id);
                    possibleClass.insert();
                    System.out.println("Class was added to Race.");
                } else {
                    System.out.println("Class " + class_id + " is already available for race " + race_id + ".");
                }

            }
        }

    }

    /**
     * Function deletes class from playable for race.
     */
    private void deleteClassFromRace() throws SQLException, IOException, Exception1 {
        BufferedReader vstup = new BufferedReader(new InputStreamReader(System.in));
        PossibleClass possibleClass = new PossibleClass();
        System.out.println("Enter race ID: ");
        int race_id = Integer.parseInt(vstup.readLine());
        Race race = RaceFinder.getInstance().findById(race_id);
        if (race == null){
            throw new Exception1("There is no such race with this ID");
        } else {
            System.out.println("Enter class ID: ");
            int class_id = Integer.parseInt(vstup.readLine());
            Klass klass = KlassFinder.getInstance().findById(class_id);
            if(klass == null){
                throw new Exception1("There is no such class with this ID");
            } else {
                PossibleClass p = PossibleClassFinder.getInstance().find(race_id, class_id);
                if (p == null){
                    System.out.println("Class " + class_id + " was not available for race " + race_id + ".");
                } else {
                    p.delete();
                    System.out.println("Class " + class_id + " is now unavailable for race " + race_id + ".");
                }
            }
        }
    }

    /**
     * Function deletes race from database.
     */
    private void deleteARace() throws SQLException, IOException, Exception1 {
        BufferedReader vstup = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter race ID: ");
        int race_id = Integer.parseInt(vstup.readLine());

        Race race = RaceFinder.getInstance().findById(race_id);
        if(race == null){
            throw new Exception1("There is no such race with this ID");
        } else {
            race.delete();
            System.out.println("Race " + race_id + " was successfully deleted.");
        }
    }

    /**
     * Function to list all existing classes.
     */
    private void listAllClasses() throws SQLException{
        for(Klass klass : KlassFinder.getInstance().findAll()){
            KlassPrinter.getInstance().print(klass);
        }
    }

    /**
     * Function creates and inserts new class into databse.
     */
    private void createAClass() throws SQLException, IOException {
        BufferedReader vstup = new BufferedReader(new InputStreamReader(System.in));
        Klass klass = new Klass();

        System.out.println("Enter name: ");
        klass.setName(vstup.readLine());
        System.out.println("Enter info: ");
        klass.setInfo(vstup.readLine());
        System.out.println("Enter starting HP of class: ");
        klass.setClass_hp(Double.parseDouble(vstup.readLine()));
        System.out.println("Enter starting POWER of class: ");
        klass.setClass_power(Double.parseDouble(vstup.readLine()));
        System.out.println("Enter starting DEFENSE of class: ");
        klass.setClass_defense(Double.parseDouble(vstup.readLine()));

        klass.insert();

        System.out.println("New class has been successfully created.");
        System.out.println("Class is currently unplayable for any race.");
        System.out.print("Class ID is: ");
        System.out.println(klass.getId());
    }

    /**
     * Lists all skills available for selected class.
     */
    private void showSkills() throws SQLException, IOException, Exception1 {
        BufferedReader vstup = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter class ID: ");
        int class_id = Integer.parseInt(vstup.readLine());
        Klass klass = KlassFinder.getInstance().findById(class_id);
        if(klass == null){
            throw new Exception1("There is no such class with this ID.");
        } else {
            System.out.println("Class " + class_id + " can use those skills: ");
            for(Available a : AvailableFinder.getInstance().findAvailable(class_id)){
                Skill skill = SkillFinder.getInstance().findById(a.getSkill_id());
                SkillPrinter.getInstance().print(skill);
            }
        }
    }
    private void updateSkills() throws SQLException, IOException {
        BufferedReader vstup = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕");
        System.out.println("||           Options             ||");
        System.out.println("||===============================||");
        System.out.println("|| 1. Add new skill to class     ||");
        System.out.println("|| 2. Delete skill from class    ||");
        System.out.println("|| 3. Update skill               ||");
        System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■");
        String zo_vstupu = vstup.readLine();
        try{
            switch (zo_vstupu){
                case "1":   addSkillToClass(); break;
                case "2":   deleteSkillFromClass(); break;
                case "3":   updateSkill(); break;
                default:    System.out.println("Unknown option"); break;
            }
        } catch (SQLException | Exception1 e){
            throw new RuntimeException(e);
        }
    }

    /**
     * Function adds new skill for selected class
     */
    private void addSkillToClass() throws SQLException, IOException, Exception1 {
        BufferedReader vstup = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter class ID: ");
        int class_id = Integer.parseInt(vstup.readLine());
        Klass klass = KlassFinder.getInstance().findById(class_id);
        if(klass == null){
            throw new Exception1("There is no such class with this ID.");
        } else {
            System.out.println("Enter skill ID: ");
            int skill_id = Integer.parseInt(vstup.readLine());
            Skill skill = SkillFinder.getInstance().findById(skill_id);
            if(skill == null){
                throw new Exception1("There is no such skill with this ID.");
            } else {
                Available a = AvailableFinder.getInstance().find(skill_id, class_id);
                if(a == null){
                    Available available = new Available();
                    available.setSkill_id(skill_id);
                    available.setClass_id(class_id);

                    available.insert();
                    System.out.println("Class " + class_id + " is now able to use skill " + skill_id + ".");
                } else {
                    System.out.println("Class " + class_id + " is already able to use skill " + skill_id + ".");
                }
            }
        }
    }

    /**
     * Function deletes skill from selected class
     */
    private void deleteSkillFromClass() throws SQLException, IOException, Exception1 {
        BufferedReader vstup = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter class ID: ");
        int class_id = Integer.parseInt(vstup.readLine());
        Klass klass = KlassFinder.getInstance().findById(class_id);
        if(klass == null){
            throw new Exception1("There is no such class with this ID.");
        } else {
            System.out.println("Enter skill ID: ");
            int skill_id = Integer.parseInt(vstup.readLine());
            Skill skill = SkillFinder.getInstance().findById(skill_id);
            if(skill == null){
                throw new Exception1("There is no such skill with this ID.");
            } else {
                Available a = AvailableFinder.getInstance().find(skill_id, class_id);
                if(a == null){
                    System.out.println("Class " + class_id + " is already unable to use skill " + skill_id + ".");
                } else {
                    Available available = new Available();
                    available.setSkill_id(skill_id);
                    available.setClass_id(class_id);

                    available.delete();
                    System.out.println("Class " + class_id + " is now unable to use skill " + skill_id + ".");
                }
            }
        }
    }

    /**
     * Function allows to buff or nerf selected skill
     */
    private void updateSkill() throws SQLException, IOException, Exception1 {
        BufferedReader vstup = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter skill ID: ");
        int skill_id = Integer.parseInt(vstup.readLine());
        Skill s = SkillFinder.getInstance().findById(skill_id);
        if(s == null){
            throw new Exception1("There is no such skill with this ID.");
        } else {
            Skill skill = new Skill();
            System.out.println("Enter new effectivity");
            skill.setEffectivity(Double.parseDouble(vstup.readLine()));
            skill.setId(skill_id);
            skill.setName(s.getName());
            skill.setType(s.getType());

            skill.update();
            System.out.println("Skill was updated and those are new stats: ");
            SkillPrinter.getInstance().print(skill);
            System.out.println("===============================");
        }
    }

    /**
     * Function allows to delete selected class from database.
     */
    private void deleteAClass() throws SQLException, IOException, Exception1 {
        BufferedReader vstup = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter class ID: ");
        int class_id = Integer.parseInt(vstup.readLine());
        Klass klass = KlassFinder.getInstance().findById(class_id);
        if(klass == null){
            throw new Exception1("There is no such class with this ID.");
        } else {
            klass.delete();
            System.out.println("Class " + class_id + " was successfully deleted.");
        }
    }

    /**
     * Function prints out all payments made by selected player.
     */
    private void showPayments() throws SQLException, IOException, Exception1 {
        BufferedReader vstup = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter player ID: ");
        int player_id = Integer.parseInt(vstup.readLine());
        Player player = PlayerFinder.getInstance().findById(player_id);
        if(player == null){
            throw new Exception1("No player with this ID");
        }
        System.out.println("| Payments of player " + player_id +" |");
        System.out.println("-------------------------");
        for(Credits credits : CreditsFinder.getInstance().findAllById(player_id)){
            CreditsPrinter.getInstance().print(credits);
        }
        System.out.println("-------------------------");
    }

    /**
     * Function takes from user ID of challenger and challenged character and simulates battle between them
     */
    private void battle() throws SQLException, IOException, Exception1 {
        BufferedReader vstup = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter ID of challenger character: ");
        int character1_id = Integer.parseInt(vstup.readLine());
        System.out.println("Enter ID of challenged character: ");
        int character2_id = Integer.parseInt(vstup.readLine());
        Battle battle = new Battle();
        battle.setCharacter_1_id(character1_id);
        battle.setCharacter_2_id(character2_id);
        battle.start();
    }

    private void shop() throws SQLException, IOException {
        BufferedReader vstup = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕♕");
        System.out.println("||           Options             ||");
        System.out.println("||===============================||");
        System.out.println("|| 1. Change current look        ||");// OK _ (vyrob na prvkovu aby si vyuzil goods a market))
        System.out.println("|| 2. Sell character             ||");
        System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■");
        String zo_vstupu = vstup.readLine();
        try{
            switch (zo_vstupu){
                case "1":   image_change(); break;
                case "2":   char_sell(); break;
                default:    System.out.println("Unknown option"); break;
            }
        } catch (SQLException | IOException | Exception1 e){
            throw new RuntimeException(e);
        }
    }

    /**
     * Function to change image if character. It takes players ID and characters ID + new values for look and calls other function
     */
    private void image_change() throws SQLException, IOException, Exception1 {
        BufferedReader vstup = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter player ID: ");
        int player_id = Integer.parseInt(vstup.readLine());
        System.out.println("Enter character ID: ");
        int character_id = Integer.parseInt(vstup.readLine());
        ImageChanger img = new ImageChanger();
        img.setChar_id(character_id);
        img.setPlayer_id(player_id);
        System.out.println("Enter new head:  ");
        img.setHead(Integer.parseInt(vstup.readLine()));
        System.out.println("Enter new body:  ");
        img.setBody(Integer.parseInt(vstup.readLine()));
        System.out.println("Enter new hair:  ");
        img.setHair(Integer.parseInt(vstup.readLine()));
        System.out.println("Enter new shirt: ");
        img.setShirt(Integer.parseInt(vstup.readLine()));
        System.out.println("Enter new pants: ");
        img.setPants(Integer.parseInt(vstup.readLine()));

        img.update_test();
        System.out.println("Image of your character was changed.");

    }

    /**
     * Function will get from user ID of seller, buyer and account that wants to be exchanged. After that it calls other function
     */
    private void char_sell() throws SQLException, IOException, Exception1 {
        BufferedReader vstup = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter seller player ID: ");
        int player_seller_id = Integer.parseInt(vstup.readLine());
        System.out.println("Enter buyer player ID: ");
        int player_buyer_id = Integer.parseInt(vstup.readLine());
        System.out.println("Enter character ID: ");
        int character_id = Integer.parseInt(vstup.readLine());

        TradeCharacter the_sell = new TradeCharacter();
        the_sell.setSeller_id(player_seller_id);
        the_sell.setBuyer_id(player_buyer_id);
        the_sell.setCharacter_id(character_id);
        the_sell.exchange_test();

        System.out.println("Exchange was successful.");
    }

    /**
     * Function will call finder to get list of all weeks and than prints them out
     */
    private void jacks() throws SQLException{
        for(Jack jack : JackFinder.getInstance().findAll()){
            JackPrinter.getInstance().print(jack);
        }
    }

    /**
     * Function will get from user player id, and 2 characters id he wants to "merge"
     * Function asks for new name of character and calls other classes to resolve it
     * At the end it prints out newly created character
     */
    private void metamorphosis() throws SQLException, IOException, Exception1 {
        BufferedReader vstup = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter player ID: ");
        int player_id = Integer.parseInt(vstup.readLine());
        System.out.println("Enter ID of first character: ");
        int character1_id = Integer.parseInt(vstup.readLine());
        System.out.println("Enter ID of second character: ");
        int character2_id = Integer.parseInt(vstup.readLine());
        Metamorphosis metamorphosis = new Metamorphosis();
        metamorphosis.setPlayer_id(player_id);
        metamorphosis.setCharacter_1_id(character1_id);
        metamorphosis.setCharacter_2_id(character2_id);
        System.out.println("Set new name for character: ");
        String name = vstup.readLine();
        metamorphosis.setName(name);
        Character new_character = metamorphosis.fuse_test();
        System.out.println("Your new character: ");
        CharacterPrinter.getInstance().print(new_character);

    }

    /**
     * Function will call BountyHunter
     * (Currently it needs input month because of generated data but otherwise it would use date to get month value)
     */
    private void bountyhunter() throws IOException, SQLException, Exception1 {
        //currently because of generated data
        BufferedReader vstup = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter month: ");
        int month = Integer.parseInt(vstup.readLine());
        // int month = local.Date.getMonthValue();
        BountyHunter bountyHunter = new BountyHunter();
        bountyHunter.setMonth(month);
        bountyHunter.resolve();
        BountyHunterPrinter.getInstance().print(bountyHunter);
    }

    private void extentstat() throws SQLException, Exception1 {
        System.out.println("Top 10% of players: ");
        System.out.println("====================");
        for (ExtentStatistic extentStatistic : ExtentStatisticFinder.getInstance().findTop()){
            ExtentStatisticPrinter.getInstance().print(extentStatistic);
        }
        System.out.println();
        System.out.println("Rest 90% of players:");
        System.out.println("====================");
        for (ExtentStatistic extentStatistic : ExtentStatisticFinder.getInstance().findOthers()){
            ExtentStatisticPrinter.getInstance().print(extentStatistic);
        }
    }

}
