
INSERT INTO users(user_id, username, password_hash, role)
VALUES
    (8, 'Thunder', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_BREWER'),
    (9, 'Gorges', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_BREWER'),
    (10, 'Backwoods', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_BREWER');

INSERT INTO breweries(brewery_id, brewery_name, owner_id, brewery_img, description, is_active, address, city, state, zip)
VALUES
    (1001,	'Thunder Island Brewing Company', '8',		'thunder_island.png', 	'a community-focused craft brewery committed to brewing distinctive beers while celebrating diversity, outdoor adventure, and all that the Gorge has to offer.', TRUE, '601 Wa Na Pa St.', 'Cascade Locks', 'OR', '97014'),
    (1002,	'Gorges Beer Co.',	'9',	'gorges.png', 	'With dreams as vast as the Columbia Gorge, our team of brewers and beer enthusiasts came together in 2019 to change the way we drink beer in Cascade Locks. Come join us before or after a hike while you''re out exploring the Gorge!', TRUE, '390 Wa Na Pa St.', 'Cascade Locks', 'OR', '97014'),
    (1003,	'Backwoods Brewing Company', '10',		'backwoods.png',	'Where the Gifford Pinchot National Forest meets the Carson valley, you will find us busily brewing craft beers from the best ingredients found in the Northwest.',	TRUE, '1162 Wind River Hwy', 'Carson', 'WA', '98610');

INSERT INTO beers(beer_name, beer_img, description, abv, beer_type, brewery_id, is_active)
VALUES
    ('Glacier Lily Golden',  'glacier_lily_golden.png',  'Golden Ale made with the same Mt. Hood snowmelt that gives way to these delicate flowers. Crisp and bready with a hit of citrus.',	5.2,  'Golden Ale',  1001,  TRUE),
    ('Dog Track Amber',	'dog_track.png',	'Dog Track chases aromas of brown bread and molasses and turns the corner to flavors of stone fruit, apricot jam, dates, and toffee.',	6,	'Amber Ale',	1001,	TRUE),
    ('140 IPA',	'140_ipa.png',	'Drops flavors of mango, grapefruit, kumquat, cataloupe, and finishes with a pithy bitterness.',	6.8,	'IPA',	1001,	TRUE),
    ('Low-Key Mischief',	'low_key.png',	'Named for Neil Gaiman''s version of the Norse god of trickery and mischief, and throws notes of bright citrus and tropical fruit.',	5.8,	'Pale Ale',	1001,	FALSE),
    ('Smoldering Embers Breakfast Beer',	'smoldering.png',	'Harmonious blend of fresh tomatoes, celery, black peppercorns, bay leaf, rosemary, sage, and dried chillies has a vegetable and herb quality followed by a slight heat.',	5,	'Herbed Beer',	1002,	TRUE),
    ('Sternwheeler Stout',	'sternwheeler.png',	'Dry stout, nice roasty flavor. Coffee and dark chocolate.',	5.5,	'Stout - American',	1002,	TRUE),
    ('Trailhead Red',	'trailhead.png',	'Deep chestnut in color, with totasted malt and biscuit undertones. A perfect balance of hop and malt.',	5.8,	'Red Ale',	1002,	TRUE),
    ('Angel''s Rest IPA',	'angels_rest.png',	'Notes of grapefruit, lime and pine, and low bitterness.  Like it''s namesake trail, will bring you a little closer to Heaven.',	6,	'IPA',	1002,	FALSE),
    ('Off Grid IPA',	'off_grid.png',	'Pine needles and Chinook hops give a piney, floral nose and Mosaic hops provide a citrus balance.  Full bodied with the addition of rye and white wheat.',	6,	'IPA',	1003,	TRUE),
    ('Blueberry Wheat',	'blueberry.png',	'Pleasant blueberry aroma with a balanced taste. Light bodied and awesomely refreshing. *contains lactose.',	5.2,	'Wheat',	1003,	TRUE),
    ('Hazy In The Gorge',	'hazy.png',	'Citrus forward hops highlight flavors of grapefruit and orange, with light notes of pine.  A mellow malt finish rounds out this hazy IPA.',	6,	'IPA',	1003,	TRUE),
    ('River Pig Lager',	'river_pig.png',	'Light bodied lager with a mild malt flavor. Refreshing and light.',	4.5,	'Lager',	1003,	FALSE);


