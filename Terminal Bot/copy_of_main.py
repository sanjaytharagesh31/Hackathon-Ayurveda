# cmd line interface for unit conversions
from termcolor import colored
import json
import re
import jellyfish

from prompt_toolkit import prompt
from prompt_toolkit.completion import WordCompleter
from prompt_toolkit import PromptSession
from prompt_toolkit.history import InMemoryHistory
from prompt_toolkit.auto_suggest import AutoSuggestFromHistory
from prompt_toolkit.styles import Style
from prompt_toolkit import print_formatted_text

session = PromptSession()

data_field = '''{
    "ayurveda": {
        "charaka": {
            "weight": {
                "anu": 0,
                "paramanu": 0,
                "truti": 0,
                "liksha": 0,
                "vanshi": 0,
                "dhwanshi": 0.00005425,
                "trasrenu": 0,
                "yuka": 0,
                "maricha": 0.0003255,
                "raja": 0,
                "rajika": 0,
                "rakta sarshapa": 0.0019531,
                "sarshapa": 0,
                "tandula": 0.015625,
                "dhanyamasha": 0.03125,
                "yava": 0.0625,
                "ratti": 0,
                "gunja": 0,
                "andika": 0.250,
                "suvarna mashaka": 0,
                "valla": 0,
                "nishpava": 0,
                "mashaka": 1,
                "hema": 1,
                "dhamaka": 0,
                "dhanyaka": 1,
                "masha": 0,
                "shana": 3,
                "tanka": 0,
                "nishka": 0,
                "kala": 0,
                "kola": 6,
                "kshudraka": 0,
                "kshudra": 0,
                "morataka": 0,
                "vataka": 0,
                "drankshana": 6,
                "badara": 6,
                "suvarna": 12,
                "dharana": 0,
                "karsha": 12,
                "panimanika": 0,
                "aksha": 12,
                "pichu": 12,
                "panitala": 12,
                "kinchitpani": 0,
                "tinduka": 12,
                "vidalpadaka": 12,
                "shodashika": 48,
                "karamadhya": 0,
                "hansapada": 0,
                "kavalagraha": 12,
                "udumbara": 0,
                "tola": 0,
                "nishkachatushtya": 0,
                "shukti": 24,
                "palardha": 24,
                "ardhapala": 0,
                "ashtamika": 24,
                "pala": 48,
                "mushti": 48,
                "amra": 48,
                "chaturthika": 48,
                "prakuncha": 48,
                "shodashi": 0,
                "bilva": 48,
                "prasrita": 96,
                "kudava": 192,
                "anjali": 192,
                "ardhasharavaka": 0,
                "ashtamana": 96,
                "manika": 384,
                "sharava": 0,
                "ashtapala": 0,
                "prastha": 768,
                "shubha": 0,
                "adhaka": 3072,
                "bhajana": 0,
                "kansapatra": 0,
                "patraka": 0,
                "tula": 4800,
                "drona": 12288,
                "kalasha": 12288,
                "nalvana": 12288,
                "armana": 12288,
                "unmana": 12288,
                "ghata": 12288,
                "rashi": 0,
                "shurpa": 24576,
                "kumbha": 24576,
                "droni": 0,
                "vahi": 0,
                "goni": 49152,
                "bhara": 49152,
                "khari": 49152,
                "vaha": 786432,
                "gram": 1,
                "kilogram": 1000,
                "milligram": 0.001
            },
            "time": {

            },
            "length": {

            }
        },
        "sushruta": {
            "weight": {
                "anu": 0,
                "paramanu": 0,
                "truti": 0,
                "liksha": 0,
                "vanshi": 0,
                "dhwanshi": 0,
                "trasrenu": 0,
                "yuka": 0,
                "maricha": 0,
                "raja": 0,
                "rajika": 0,
                "rakta sarshapa": 0,
                "sarshapa": 0,
                "tandula": 0,
                "dhanyamasha": 0,
                "yava": 0,
                "ratti": 0,
                "gunja": 0,
                "andika": 0,
                "suvarna mashaka": 0.375,
                "valla": 0,
                "nishpava": 0.42105,
                "mashaka": 0,
                "hema": 0,
                "dhamaka": 0,
                "dhanyaka": 0,
                "masha": 0,
                "shana": 0,
                "tanka": 0,
                "nishka": 0,
                "kala": 0,
                "kola": 0,
                "kshudraka": 0,
                "kshudra": 0,
                "morataka": 0,
                "vataka": 0,
                "drankshana": 0,
                "badara": 0,
                "suvarna": 6,
                "dharana": 8,
                "karsha": 12,
                "panimanika": 0,
                "aksha": 0,
                "pichu": 0,
                "panitala": 0,
                "kinchitpani": 0,
                "tinduka": 0,
                "vidalpadaka": 0,
                "shodashika": 0,
                "karamadhya": 0,
                "hansapada": 0,
                "kavalagraha": 0,
                "udumbara": 0,
                "tola": 0,
                "nishkachatushtya": 0,
                "shukti": 0,
                "palardha": 0,
                "ardhapala": 0,
                "ashtamika": 0,
                "pala": 48,
                "mushti": 0,
                "amra": 0,
                "chaturthika": 0,
                "prakuncha": 0,
                "shodashi": 0,
                "bilva": 0,
                "prasrita": 0,
                "kudava": 192,
                "anjali": 0,
                "ardhasharavaka": 0,
                "ashtamana": 0,
                "manika": 0,
                "sharava": 0,
                "ashtapala": 0,
                "prastha": 768,
                "shubha": 0,
                "adhaka": 3072,
                "bhajana": 0,
                "kansapatra": 0,
                "patraka": 0,
                "tula": 4800,
                "drona": 12288,
                "kalasha": 0,
                "nalvana": 0,
                "armana": 0,
                "unmana": 0,
                "ghata": 0,
                "rashi": 0,
                "shurpa": 0,
                "kumbha": 0,
                "droni": 0,
                "vahi": 0,
                "goni": 0,
                "bhara": 96000,
                "khari": 0,
                "vaha": 0,
                "gram": 1,
                "kilogram": 1000,
                "milligram": 0.001
            },
            "time": {
                "ksanas": 0,
                "lava": 0,
                "nimesha": 0,
                "kashtha": 4.66,
                "kala": 140,
                "ghati": 0,
                "muhurta": 2880,
                "ahoratra": 86400,
                "paksha": 1296000,
                "masa": 2592000,
                "ritu": 5184000,
                "ayana": 15552000,
                "samvatsara": 31104000,
                "yuga": 155520000,
                "ahoratra deva": 0,
                "ahoratra pitra": 0,
                "second": 1,
                "minute": 60,
                "hour": 3600,
                "day": 86400,
                "month": 2628000,
                "year": 31540000
            },
            "length": {

            }
        },
        "sharangadhara": {
            "weight": {
                "anu": 0,
                "paramanu": 0.0000012,
                "truti": 0,
                "liksha": 0,
                "vanshi": 0.0000361,
                "dhwanshi": 0,
                "trasrenu": 0.0000361,
                "yuka": 0,
                "maricha": 0.000217,
                "raja": 0,
                "rajika": 0.001302,
                "rakta sarshapa": 0,
                "sarshapa": 0.003906,
                "tandula": 0,
                "dhanyamasha": 0,
                "yava": 0.03125,
                "ratti": 0.125,
                "gunja": 0.125,
                "andika": 0,
                "suvarna mashaka": 0,
                "valla": 0,
                "nishpava": 0,
                "mashaka": 0.750,
                "hema": 0.750,
                "dhamaka": 0,
                "dhanyaka": 0.750,
                "masha": 0,
                "shana": 3,
                "tanka": 3,
                "nishka": 0,
                "kala": 0,
                "kola": 6,
                "kshudraka": 6,
                "kshudra": 0,
                "morataka": 0,
                "vataka": 6,
                "drankshana": 6,
                "badara": 0,
                "suvarna": 12,
                "dharana": 3,
                "karsha": 12,
                "panimanika": 12,
                "aksha": 12,
                "pichu": 12,
                "panitala": 12,
                "kinchitpani": 12,
                "tinduka": 12,
                "vidalpadaka": 12,
                "shodashika": 12,
                "karamadhya": 12,
                "hansapada": 12,
                "kavalagraha": 12,
                "udumbara": 12,
                "tola": 0,
                "nishkachatushtya": 0,
                "shukti": 24,
                "palardha": 0,
                "ardhapala": 24,
                "ashtamika": 24,
                "pala": 48,
                "mushti": 48,
                "amra": 48,
                "chaturthika": 48,
                "prakuncha": 48,
                "shodashi": 48,
                "bilva": 48,
                "prasrita": 96,
                "kudava": 192,
                "anjali": 192,
                "ardhasharavaka": 192,
                "ashtamana": 192,
                "manika": 384,
                "sharava": 384,
                "ashtapala": 384,
                "prastha": 768,
                "shubha": 0,
                "adhaka": 3072,
                "bhajana": 3072,
                "kansapatra": 3072,
                "patraka": 0,
                "tula": 4800,
                "drona": 12288,
                "kalasha": 12288,
                "nalvana": 12288,
                "armana": 12288,
                "unmana": 12288,
                "ghata": 12288,
                "rashi": 12288,
                "shurpa": 24576,
                "kumbha": 24576,
                "droni": 49152,
                "vahi": 49152,
                "goni": 49152,
                "bhara": 96000,
                "khari": 196608,
                "vaha": 0,
                "gram": 1,
                "kilogram": 1000,
                "milligram": 0.001

            },
            "time": {

            },
            "length": {

            }
        },
        "rrs": {
            "weight": {
                "anu": 0.0000004463,
                "paramanu": 0,
                "truti": 0.000002678,
                "liksha": 0.00001607,
                "vanshi": 0,
                "dhwanshi": 0,
                "trasrenu": 0,
                "yuka": 0.00009645,
                "maricha": 0,
                "raja": 0.0005787,
                "rajika": 0,
                "rakta sarshapa": 0,
                "sarshapa": 0.003472,
                "tandula": 0,
                "dhanyamasha": 0,
                "yava": 0.020833,
                "ratti": 0,
                "gunja": 0.125,
                "andika": 0,
                "suvarna mashaka": 0,
                "valla": 0.375,
                "nishpava": 0.250,
                "mashaka": 0,
                "hema": 0,
                "dhamaka": 0,
                "dhanyaka": 0,
                "masha": 0.750,
                "shana": 3,
                "tanka": 0,
                "nishka": 3,
                "kala": 3,
                "kola": 6,
                "kshudraka": 0,
                "kshudra": 0,
                "morataka": 0,
                "vataka": 6,
                "drankshana": 0,
                "badara": 0,
                "suvarna": 12,
                "dharana": 1.5,
                "karsha": 12,
                "panimanika": 0,
                "aksha": 12,
                "pichu": 0,
                "panitala": 12,
                "kinchitpani": 0,
                "tinduka": 0,
                "vidalpadaka": 12,
                "shodashika": 0,
                "karamadhya": 0,
                "hansapada": 0,
                "kavalagraha": 12,
                "udumbara": 12,
                "tola": 12,
                "nishkachatushtya": 12,
                "shukti": 24,
                "palardha": 0,
                "ardhapala": 0,
                "ashtamika": 0,
                "pala": 48,
                "mushti": 48,
                "amra": 0,
                "chaturthika": 0,
                "prakuncha": 48,
                "shodashi": 0,
                "bilva": 48,
                "prasrita": 96,
                "kudava": 192,
                "anjali": 192,
                "ardhasharavaka": 0,
                "ashtamana": 0,
                "manika": 384,
                "sharava": 0,
                "ashtapala": 0,
                "prastha": 768,
                "shubha": 1536,
                "adhaka": 3072,
                "bhajana": 0,
                "kansapatra": 0,
                "patraka": 3072,
                "tula": 4800,
                "drona": 0,
                "kalasha": 0,
                "nalvana": 0,
                "armana": 0,
                "unmana": 0,
                "ghata": 0,
                "rashi": 0,
                "shurpa": 0,
                "kumbha": 0,
                "droni": 0,
                "vahi": 0,
                "goni": 0,
                "bhara": 0,
                "khari": 0,
                "vaha": 0,
                "gram": 1,
                "kilogram": 1000,
                "milligram": 0.001
            },
            "time": {

            },
            "length": {

            }
        },
        "vpp": {
            "weight": {
                "anu": 0,
                "paramanu": 0,
                "truti": 0,
                "liksha": 0,
                "vanshi": 0,
                "dhwanshi": 0.0000361,
                "trasrenu": 0,
                "yuka": 0,
                "maricha": 0.000217,
                "raja": 0,
                "rajika": 0.001302,
                "rakta sarshapa": 0,
                "sarshapa": 0.003906,
                "tandula": 0,
                "dhanyamasha": 0,
                "yava": 0.03125,
                "ratti": 0.125,
                "gunja": 0.125,
                "andika": 0,
                "suvarna mashaka": 0,
                "valla": 0,
                "nishpava": 0,
                "mashaka": 0.750,
                "hema": 0.750,
                "dhamaka": 0.750,
                "dhanyaka": 0,
                "masha": 0.750,
                "shana": 3,
                "tanka": 3,
                "nishka": 0,
                "kala": 0,
                "kola": 6,
                "kshudraka": 0,
                "kshudra": 6,
                "morataka": 6,
                "vataka": 0,
                "drankshana": 6,
                "badara": 0,
                "suvarna": 12,
                "dharana": 3,
                "karsha": 12,
                "panimanika": 12,
                "aksha": 12,
                "pichu": 12,
                "panitala": 12,
                "kinchitpani": 12,
                "tinduka": 12,
                "vidalpadaka": 12,
                "shodashika": 12,
                "karamadhya": 12,
                "hansapada": 12,
                "kavalagraha": 12,
                "udumbara": 12,
                "tola": 12,
                "nishkachatushtya": 0,
                "shukti": 24,
                "palardha": 0,
                "ardhapala": 24,
                "ashtamika": 24,
                "pala": 48,
                "mushti": 48,
                "amra": 0,
                "chaturthika": 48,
                "prakuncha": 48,
                "shodashi": 48,
                "bilva": 48,
                "prasrita": 96,
                "kudava": 192,
                "anjali": 192,
                "ardhasharavaka": 192,
                "ashtamana": 192,
                "manika": 384,
                "sharava": 384,
                "ashtapala": 384,
                "prastha": 768,
                "shubha": 0,
                "adhaka": 3072,
                "bhajana": 3072,
                "kansapatra": 3072,
                "patraka": 0,
                "tula": 4800,
                "drona": 12288,
                "kalasha": 12288,
                "nalvana": 12288,
                "armana": 12288,
                "unmana": 12288,
                "ghata": 12288,
                "rashi": 12288,
                "shurpa": 24576,
                "kumbha": 24576,
                "droni": 49152,
                "vahi": 49152,
                "goni": 49152,
                "bhara": 96000,
                "khari": 196608,
                "vaha": 0,
                "gram": 1,
                "kilogram": 1000,
                "milligram": 0.001
            },
            "time": {

            },
            "length": {

            }
        },
        "api": {
            "weight": {
                "anu": 0,
                "paramanu": 0,
                "truti": 0,
                "liksha": 0,
                "vanshi": 0,
                "dhwanshi": 0,
                "trasrenu": 0,
                "yuka": 0,
                "maricha": 0,
                "raja": 0,
                "rajika": 0,
                "rakta sarshapa": 0,
                "sarshapa": 0,
                "tandula": 0,
                "dhanyamasha": 0,
                "yava": 0,
                "ratti": 0.125,
                "gunja": 0.125,
                "andika": 0,
                "suvarna mashaka": 0,
                "valla": 0,
                "nishpava": 0,
                "mashaka": 0,
                "hema": 0,
                "dhamaka": 0,
                "dhanyaka": 0,
                "masha": 1,
                "shana": 0,
                "tanka": 0,
                "nishka": 0,
                "kala": 0,
                "kola": 0,
                "kshudraka": 0,
                "kshudra": 0,
                "morataka": 0,
                "vataka": 0,
                "drankshana": 0,
                "badara": 0,
                "suvarna": 0,
                "dharana": 0,
                "karsha": 12,
                "panimanika": 0,
                "aksha": 0,
                "pichu": 0,
                "panitala": 0,
                "kinchitpani": 0,
                "tinduka": 0,
                "vidalpadaka": 0,
                "shodashika": 0,
                "karamadhya": 0,
                "hansapada": 0,
                "kavalagraha": 0,
                "udumbara": 0,
                "tola": 12,
                "nishkachatushtya": 0,
                "shukti": 24,
                "palardha": 0,
                "ardhapala": 0,
                "ashtamika": 0,
                "pala": 48,
                "mushti": 0,
                "amra": 0,
                "chaturthika": 0,
                "prakuncha": 0,
                "shodashi": 0,
                "bilva": 0,
                "prasrita": 96,
                "kudava": 192,
                "anjali": 0,
                "ardhasharavaka": 0,
                "ashtamana": 0,
                "manika": 384,
                "sharava": 0,
                "ashtapala": 0,
                "prastha": 768,
                "shubha": 0,
                "adhaka": 3072,
                "bhajana": 0,
                "kansapatra": 0,
                "patraka": 0,
                "tula": 4800,
                "drona": 12288,
                "kalasha": 0,
                "nalvana": 0,
                "armana": 0,
                "unmana": 0,
                "ghata": 0,
                "rashi": 0,
                "shurpa": 24576,
                "kumbha": 0,
                "droni": 49152,
                "vahi": 49152,
                "goni": 0,
                "bhara": 96000,
                "khari": 196608,
                "vaha": 0,
                "gram": 1,
                "kilogram": 1000,
                "milligram": 0.001
            },
            "time": {
                "ksanas": 0,
                "lava": 0,
                "nimesha": 0,
                "kashtha": 4.66,
                "kala": 140,
                "ghati": 1440,
                "muhurta": 2880,
                "ahoratra": 86400,
                "paksha": 1296000,
                "masa": 2592000,
                "ritu": 5184000,
                "ayana": 15552000,
                "samvatsara": 31104000,
                "yuga": 155520000,
                "ahoratra deva": 31104000,
                "ahoratra pitra": 2592000,
                "second": 1,
                "minute": 60,
                "hour": 3600,
                "day": 86400,
                "month": 2628000,
                "year": 31540000
            },
            "length": {
                "yavodara": 0.24,
                "angula": 1.95,
                "vitasti": 22.86,
                "aratni": 41.91,
                "hasta": 45.72,
                "nripa hasta": 55.88,
                "rajhasta": 55.88,
                "vyama": 182.88,
                "metre": 100,
                "centimetre": 1,
                "kilometre": 100000,
                "inch": 2.54,
                "millimetre": 0.1
            }
        }
    },
    "siddha": {
        "api": {
            "weight": {
                "grain": 0.065,
                "uluntu": 0.065,
                "kunri": 0.13,
                "yavam": 0.0325,
                "mancadi": 0.26,
                "masam": 0.78,
                "pana edai": 0.488,
                "varakan edai": 4.16,
                "kazancu": 5.12,
                "palam(pakka)": 41.6,
                "kahcu": 10.4,
                "kaica": 10.4,
                "tola": 11.7,
                "palam": 35,
                "cer": 280,
                "vicai": 1400,
                "tukku": 1750,
                "tulam": 3500,
                "gram": 1,
                "kilogram": 1000,
                "milligram": 0.001
            },
            "time": {
                "nodi": 1,
                "nazikai": 1440,
                "mukurttam": 5400,
                "yamam": 10800,
                "paksam": 1296000,
                "matam": 2592000,
                "mandalam": 3888000,
                "kalam": 5184000,
                "ayanam": 15552000,
                "second": 1,
                "minute": 60,
                "hour": 3600,
                "day": 86400,
                "month": 2628000,
                "year": 31540000
            },
            "length": {
                "virarkadai": 1.95,
                "can": 22.86,
                "muzam": 45.72,
                "pakam": 182.88,
                "metre": 100,
                "centimetre": 1,
                "kilometre": 100000,
                "inch": 2.54,
                "millimetre": 0.001
            },
            "volume": {
                "nel": 0.09333,
                "codu": 33.6,
                "azakku": 168,
                "uzakku": 336,
                "uri": 672,
                "nazi": 1340,
                "padi": 1340,
                "kuruni": 5370,
                "marakkal": 5370,
                "patakku": 10700,
                "mukkuruni": 16100,
                "tuni": 21500,
                "kalam": 64500,
                "millilitre": 1,
                "litre": 1000,
                "dram": 3.69667,
                "ounce": 29.5735,
                "tekkarandi": 3.69667,
                "kuppikarandi": 709.765,
                "tirttakkarandi": 1.33,
                "ney karanda": 4.0,
                "uccikkarandi": 1.6,
                "paladai": 30,
                "enneykkarandi": 240
            }
        }
    },
    "unani": {
        "api": {
            "weight": {
                "grain": 0.01558,
                "chawal": 0.01558,
                "jo": 0.06332,
                "ratti": 0.125,
                "surkha": 0.125,
                "danga": 0.500,
                "masha": 0.970,
                "miskala": 4.5,
                "tola": 11.664,
                "rupya": 57.326,
                "aaqya": 350,
                "ratala": 468.5,
                "sera": 937,
                "gram": 1,
                "kilogram": 1000,
                "milligram": 0.001
            },
            "volume": {
                "tola": 12,
                "millilitre": 1,
                "litre": 1000
            }
        }
    }
}'''

default_unit_field = '''{
    "weight": {
        "from_unit": "gram",
        "to_unit": "kilogram"
    },
    "time": {
        "from_unit": "second",
        "to_unit": "minute"
    },
    "length": {
        "from_unit": "centimetre",
        "to_unit": "metre"
    },
    "volume": {
        "from_unit": "millilitre",
        "to_unit": "litre"
    }
}'''

words = ['ayurveda', 'siddha', 'unani', 'charaka', 'sushruta', 'sharangadhara', 'rrs', 'vaidyaka paribhasha pradeepa', 'vpp', 'api',
'weight', 'length', 'time', 'volume','anu','paramanu','truti','liksha','vanshi','dhwanshi','trasrenu','yuka','maricha','raja','rajika',
'rakta sarshapa','sarshapa','tandula','dhanyamasha','yava','ratti','gunja','andika','suvarna mashaka','valla','nishpava','mashaka',
'hema','dhamaka','dhanyaka','masha','shana','tanka','nishka','kala','kola','kshudraka','kshudra','morataka','vataka','drankshana',
'badara','suvarna','dharana','karsha','panimanika','aksha','pichu','panitala','kinchitpani','tinduka',
'vidalpadaka','shodashika','karamadhya','hansapada','kavalagraha','udumbara','tola','nishkachatushtya',
'shukti','palardha','ardhapala','ashtamika','pala','mushti','amra','chaturthika','prakuncha','shodashi',
'bilva','prasrita','kudava','anjali','ardhasharavaka','ashtamana','manika','sharava','ashtapala',
'prastha','shubha','adhaka','bhajana','kansapatra','patraka','tula','drona','kalasha','nalvana',
'armana','unmana','ghata','rashi','shurpa','kumbha','droni','vahi','goni','bhara','khari','vaha',
'gram','kilogram','milligram','ksanas','lava','nimesha','kashtha','ghati','muhurta','ahoratra',
'paksha','masa','ritu','ayana','samvatsara','yuga','ahoratra deva','ahoratra pitra','second',
'minute','hour','day','month','year','yavodara','angula','vitasti','aratni','hasta',
'nripa hasta','rajhasta','vyama','metre','centimetre','kilometre','inch','millimetre',
'grain','uluntu','kunri','yavam','mancadi','masam','pana edai','varakan edai','kazancu',
'palam(pakka)','kahcu','kaica','palam','cer','vicai','tukku','tulam','nodi','nazikai',
'mukurttam','yamam','paksam','matam','mandalam','kalam','ayanam','virarkadai','can',
'muzam','pakam','nel','codu','azakku','uzakku','uri','nazi','padi','kuruni','marakkal','patakku',
'mukkuruni','tuni','millilitre','litre','dram','ounce','tekkarandi','kuppikarandi','tirttakkarandi',
'ney karanda','uccikkarandi','paladai','enneykkarandi','chawal','jo','surkha','danga','miskala',
'rupya','aaqya','ratala','sera']

systems = ['ayurveda', 'siddha', 'unani']
authors = ['charaka', 'sushruta', 'sharangadhara', 'rrs', 'vaidyaka paribhasha pradeepa', 'vpp', 'api']
metrics = ['weight', 'time', 'length', 'volume']

data_field = json.loads(data_field)
default_unit_field = json.loads(default_unit_field)

system = 'ayurveda'
author = 'api'
metric = 'weight'

pre_from_unit = default_unit_field[metric]['from_unit']
pre_to_unit = default_unit_field[metric]['to_unit']
pre_value = 1

def greet():
    print("\nHello user! Welcome to AYUSH unit converter.")
    help_rules()

def help_rules():
    print("I am here to help you. Read the following commands.\n\n")
    print("\tset         - Sets to any system, author or metric")
    print("\tfrom        - Sets the FROM unit")
    print("\tto          - Sets the TO unit")
    print("\tsystems     - Prints systems list")
    print("\tauthors     - prints authors list")
    print("\tmetrics     - Prints metrics list")
    print("\tparams      - Prints the selected system, author, metric and previous conversion")
    print("\tswap        - Calculates the reverse value of previous conversion")
    print("\thelp        - Prints the helper")
    print("\texit        - To exit from application\n\n",)
    print('To select autocomplete, enter right-> arrow')
    print("Refer the examples given")
    print("\tset ayurveda")
    print("\tset charaka")
    print("\tfrom ratti")
    print("Enter a number to convert values\n\n")

def print_beauty(l):
    for i in range(len(l)):
        print(str(i+1), l[i].title())

def show_systems():
    print("There are three systems")
    print_beauty(systems)
    print("To set a system, type set ayurveda\n")

def show_authors():
    print("There are six authors")
    print_beauty(authors)
    print("'vaidyaka paribhasha pradeepa' and  'vpp' are same authors")
    print("To set a author, type set sushruta\n")

def show_metrics():
    print("There are four metrics")
    print_beauty(metrics)
    print("To set a author, type set length\n")

def show_parameters():
    print('The selected parameters are (', system.title(), author.title(), metric.title(), ') We\'re good to go captain. You previously converted', pre_from_unit.title(), 'to', pre_to_unit.title(), '.\n')

def convert():
    unitfrom_value = data_field[system][author][metric][pre_from_unit]
    unitto_value = data_field[system][author][metric][pre_to_unit]
    ans = pre_value * (unitfrom_value / unitto_value)
    res = str(pre_value)+' '+pre_from_unit+' is '+str(ans)+' '+pre_to_unit+'.\n'
    return res

def find_similar(input_two):
    max_val = 10000
    sim_word = ""
    for w in words:
        val = jellyfish.levenshtein_distance(input_two, w)
        if(val < max_val):
            max_val = val
            sim_word = w
    return sim_word

def process_input(user_input):
    req = list([val for val in user_input if val.isalnum() or val==' '])
    req = "".join(req) 
    req = req.lower().split()

    code = ""
    keyword = ""

    if(len(req) > 2):
        req[1] = " ".join(req[1:])
        req = [req[0], req[1]]

    if(len(req) == 1):
        code = req[0]

    elif(len(req) == 2):
        code = req[0]
        if(req[1] not in words):
            keyword = find_similar(req[1])
        else:
            keyword = req[1]
    
    return code, keyword

def main_loop():
    global system
    global author
    global metric
    global pre_from_unit
    global pre_to_unit
    global pre_value

    greet()

    drop_down = words + ['set', 'systems', 'authors', 'metrics', 'params', 'swap', 'from', 'to', 'exit', 'help']
    sorted(drop_down)
    droper = WordCompleter(drop_down)
    style1 = Style.from_dict({'': '#00FF00'})

    while(True):
        
        inp = session.prompt('You: ', auto_suggest=AutoSuggestFromHistory(), completer=droper, style=style1)
        print('')
        code, keyword = process_input(inp)
        print('Bot: ', end='')

        if(code == '' and keyword == ''):
            continue
        
        if(code == 'help'):
            help_rules()

        elif(code == 'systems'):
            show_systems()

        elif(code == 'metrics'):
            show_metrics()

        elif(code == 'authors'):
            show_authors()

        elif(code == 'params'):
            show_parameters()

        elif(code == 'set'):
            if(keyword == ''):
                print("Please enter the second parameter\n")
                continue
            elif(keyword in systems):
                system = keyword
            elif(keyword in metrics):
                metric = keyword
            elif(keyword in authors):
                author = keyword
                if(keyword == 'vaidyaka paribhasha pradeepa'):
                    author = 'vpp'
            else:
                print("Please check the spelling", keyword, 'not found\n')
                continue
            
            if(system == 'unani'):
                author = 'api'
                if(metric not in ['weight', 'volume']):
                    print('Unfortunately Unani system doesn\'t support', metric, '. It only supports weight and volume measures. Let me change the metric, to weight, for you.\n')
                    metric = 'weight'
            elif(system == 'siddha'):
                author = 'api'
            elif(system == 'ayurveda'):
                if (author in ['charaka', 'sharangadhara', 'rrs', 'vpp']):
                    if (metric != 'weight'):
                        metric = 'weight'
                        print(author.title(), 'has defined only weight metric. I\'ve changed the metric to weight\n')
                elif (author == 'sushruta'):
                    if (metric not in ['weight', 'time']):
                        metric = 'weight'
                        print(author.title(), 'has defined only weight and time metrics. Metric changed to weight.\n')
                elif (author == 'api'):
                    if (metric not in ['weight', 'time', 'length']):
                        metric = 'weight'
                        print(author.title(), 'has defined only weight, time and length metrics. Metric changed to weight.\n')
            
            show_parameters()

            pre_from_unit = default_unit_field[metric]['from_unit']
            pre_to_unit = default_unit_field[metric]['to_unit']

        elif(code == 'swap'):
            pre_from_unit, pre_to_unit = pre_to_unit, pre_from_unit
            print(convert())
        
        elif(code == 'from' or code == 'to'):
            if(keyword == ''):
                print("Please enter 'from / to' unit\n")
                continue
            elif(code == 'from'):
                pre_from_unit = keyword
            elif(code == 'to'):
                pre_to_unit = keyword

            try:
                unitfrom_value = data_field[system][author][metric][pre_from_unit]
            except:
                print('Oops! I couldn\'t find', pre_from_unit, 'in', system, author, metric, '. Can you try checking the website (https://siddharthsham.github.io/ayush) for reference\n')
                continue

            try:
                unitto_value = data_field[system][author][metric][pre_to_unit]
            except:
                print('Oops! I couldn\'t find', pre_to_unit, 'in', system, author, metric, '. Can you try checking the website (https://siddharthsham.github.io/ayush) for reference\n')
                continue
            
            if(unitfrom_value == 0):
                print('I believe', author, ' has not defined', pre_from_unit, 'unit in his metrics. Refer the website (https://siddharthsham.github.io/ayush) for referecnce\n')
                continue
            elif(unitto_value == 0):
                print('I believe', author, 'has not defined', pre_to_unit, 'unit in his metrics. Refer the website (https://siddharthsham.github.io/ayush) for referecnce\n')
                continue
            else:
                print(convert())
        
        elif(code == 'exit'):
            print("Bye, ^--^")
            exit()
        
        else:
            try:
                pre_value = float(inp)
                print(convert())      
            except:
                print("Enter valid input\n")  


if __name__ == "__main__":
    main_loop()