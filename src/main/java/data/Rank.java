package data;


import enums.MarineRank;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

public final class Rank {

    private String name;
    private String imageURL;
    private int promotionScores;

    private static final Map<MarineRank, Rank> AVAILABLE_RANKS = new EnumMap<>(MarineRank.class);

    private Rank(String name, String imageURL, int promotionScores) {
        this.name = name;
        this.imageURL = imageURL;
        this.promotionScores = promotionScores;
    }

    static {
        initAvailableRanks();
    }


    private static void initAvailableRanks(){
        AVAILABLE_RANKS.put(MarineRank.SEAMAN_APPRENTICE,
                new Rank("Seaman Apprentice",  "https://cdn1.savepice.ru/uploads/2020/12/28/060166c96cb2c70d582189b1fdf1c5e8-full.png", 20));
        AVAILABLE_RANKS.put(MarineRank.SEAMAN,
                new Rank("Seaman", "https://cdn1.savepice.ru/uploads/2020/12/28/62594917c47c415e47dc221ee4b2fd1c-full.png", 50));
        AVAILABLE_RANKS.put(MarineRank.ENSIGN,
                new Rank("Ensign",  "https://cdn1.savepice.ru/uploads/2020/12/28/bf59a4a2d27e46207b0068a1ff25fe50-full.png", 100));
        AVAILABLE_RANKS.put(MarineRank.LIEUTENANT_JR_GRADE,
                new Rank("Lieutenant jr.grade",  "https://cdn1.savepice.ru/uploads/2020/12/28/3c3b52208b96af0b07b86e0635689c29-full.png", 170));
        AVAILABLE_RANKS.put(MarineRank.LIEUTENANT,
                new Rank("Lieutenant", "https://cdn1.savepice.ru/uploads/2020/12/28/101ac97f4c31936c6c43346a079098a6-full.png", 250));
        AVAILABLE_RANKS.put(MarineRank.LIEUTENANT_COMMANDER,
                new Rank("Lieutenant Commander", "https://cdn1.savepice.ru/uploads/2020/12/28/59181f4132484ea1fb7a295d8a0226c3-full.png", 350));
        AVAILABLE_RANKS.put(MarineRank.COMMANDER,
                new Rank("Commander",  "https://cdn1.savepice.ru/uploads/2020/12/28/450b27c4d697ed0cb7b4b6b38be60876-full.png", 475));
        AVAILABLE_RANKS.put(MarineRank.CAPTAIN,
                new Rank("Captain", "https://cdn1.savepice.ru/uploads/2020/12/28/fb68b689ff39651954c505797b42e4da-full.png", 625));
        AVAILABLE_RANKS.put(MarineRank.REAR_ADMIRAL_LH,
                new Rank("Rear Admiral LH",  "https://cdn1.savepice.ru/uploads/2020/12/28/f0da6397584bd1511ef649d7276af970-full.png", 800));
        AVAILABLE_RANKS.put(MarineRank.REAR_ADMIRAL,
                new Rank("Rear Admiral",  "https://cdn1.savepice.ru/uploads/2020/12/28/78363a2043ce749314a27cb6b422bb29-full.png", 1000));
        AVAILABLE_RANKS.put(MarineRank.VICE_ADMIRAL,
                new Rank("Vice Admiral", "https://cdn1.savepice.ru/uploads/2020/12/28/d32b8ce7a8d41636e9901e6baea80ccf-full.png", 1300));
        AVAILABLE_RANKS.put(MarineRank.ADMIRAL,
                new Rank("Admiral",  "https://cdn1.savepice.ru/uploads/2020/12/28/3abf41980adc6c61cd380b602891d732-full.png", 1625));
        AVAILABLE_RANKS.put(MarineRank.FLEET_ADMIRAL,
                new Rank("Admiral",  "https://cdn1.savepice.ru/uploads/2020/12/28/ee3b870d3413f701b755de8d1fed3820-full.png", 2000));
    }

    public static Rank of(MarineRank marineRank){
        return AVAILABLE_RANKS.get(marineRank);
    }


    public String getName() {
        return name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public int getPromotionScores() {
        return promotionScores;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rank rank = (Rank) o;
        return promotionScores == rank.promotionScores &&
                Objects.equals(name, rank.name) &&
                Objects.equals(imageURL, rank.imageURL);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, imageURL, promotionScores);
    }

    @Override
    public String toString() {
        return "Rank{" +
                "name='" + name + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", promotionScores=" + promotionScores +
                '}';
    }
}
