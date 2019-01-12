package unislask;

import java.util.Optional;
import java.util.HashMap;

interface Funktor{
    Optional<HashMap<Integer,Book>> search(String value);
}