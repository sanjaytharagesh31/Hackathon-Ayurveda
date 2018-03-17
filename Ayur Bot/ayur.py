def convert(unit, input):
    switcher = {
        "yavodara": input*0.24,
        "angula": input*1.95,
        "bitahasti": input*22.86,
        "aratni": input*41.91,
        "hasta": input*45.72,
        "nrpahasta": input*55.88,
        "rajahasta": input*55.88,
        "vyama": input*182.88
    }
    switcher.get(unit, "Unknown unit");