package enums;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public enum Rank {

    SEAMAN_APPRENTICE(0, "Seaman Apprentice",
            "https://cdn1.savepice.ru/uploads/2020/12/28/060166c96cb2c70d582189b1fdf1c5e8-full.png",
            20),
    SEAMAN(1, "Seaman",
            "https://cdn1.savepice.ru/uploads/2020/12/28/62594917c47c415e47dc221ee4b2fd1c-full.png",
            50),
    ENSIGN(2, "Ensign",
            "https://cdn1.savepice.ru/uploads/2020/12/28/bf59a4a2d27e46207b0068a1ff25fe50-full.png",
            100),
    LIEUTENANT_JR_GRADE(3, "Lieutenant jr.grade",
            "https://cdn1.savepice.ru/uploads/2020/12/28/3c3b52208b96af0b07b86e0635689c29-full.png",
            170),
    LIEUTENANT(4, "Lieutenant",
            "https://cdn1.savepice.ru/uploads/2020/12/28/101ac97f4c31936c6c43346a079098a6-full.png",
            250),
    LIEUTENANT_COMMANDER(5, "Lieutenant Commander",
            "https://cdn1.savepice.ru/uploads/2020/12/28/59181f4132484ea1fb7a295d8a0226c3-full.png",
            350),
    COMMANDER(6, "Commander",
            "https://cdn1.savepice.ru/uploads/2020/12/28/450b27c4d697ed0cb7b4b6b38be60876-full.png",
            475),
    CAPTAIN(7, "Captain",
            "https://cdn1.savepice.ru/uploads/2020/12/28/fb68b689ff39651954c505797b42e4da-full.png",
            625),
    REAR_ADMIRAL_LH(8, "Rear Admiral LH",
            "https://cdn1.savepice.ru/uploads/2020/12/28/f0da6397584bd1511ef649d7276af970-full.png",
            800),
    REAR_ADMIRAL(9, "Rear Admiral",
            "https://cdn1.savepice.ru/uploads/2020/12/28/78363a2043ce749314a27cb6b422bb29-full.png",
            1000),
    VICE_ADMIRAL(10, "Vice Admiral",
            "https://cdn1.savepice.ru/uploads/2020/12/28/d32b8ce7a8d41636e9901e6baea80ccf-full.png",
            1300),
    ADMIRAL(11, "Admiral",
            "https://cdn1.savepice.ru/uploads/2020/12/28/3abf41980adc6c61cd380b602891d732-full.png",
            1625),
    FLEET_ADMIRAL(12, "Admiral",
            "https://cdn1.savepice.ru/uploads/2020/12/28/ee3b870d3413f701b755de8d1fed3820-full.png",
            2000);

    private final int order;
    private final String representation;
    private final String imageURL;
    private final int promotionScores;

    private static final Map<Integer, Rank> ORDER_TO_RANKS_MAP = new HashMap<>();
    private static final Map<String, Rank> REPRESENTATION_TO_RANKS_MAP = new HashMap<>();

    Rank(int order, String representation, String imageURL, int promotionScores) {
        this.order = order;
        this.representation = representation;
        this.imageURL = imageURL;
        this.promotionScores = promotionScores;
    }

    static {
        initOrderToRanksMap();
        initRepresentationToRanksMap();
    }

    private static void initOrderToRanksMap(){
        for (Rank rank :
                Rank.values()) {
            ORDER_TO_RANKS_MAP.put(rank.order, rank);
        }
    }

    private static void initRepresentationToRanksMap(){
        for (Rank rank :
                Rank.values()) {
            REPRESENTATION_TO_RANKS_MAP.put(rank.representation, rank);
        }
    }

    @Nullable
    public static Rank ofRepresentation(String representation){
         return REPRESENTATION_TO_RANKS_MAP.get(representation);
    }

    @Nullable
    public static Rank next(Rank current){
        return ORDER_TO_RANKS_MAP.get(current.order + 1);
    }

    @Nullable
    private static Rank previous(Rank current){
        return ORDER_TO_RANKS_MAP.get(current.order - 1);
    }

    public int getPreviousPromotionScores(){
        Rank prev = previous(this);
        return (prev == null) ? 0 : prev.promotionScores;
    }

    public String getRepresentation() {
        return representation;
    }

    public String getImageURL() {
        return imageURL;
    }

    public int getPromotionScores() {
        return promotionScores;
    }
}
