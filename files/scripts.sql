CREATE TABLE client (
	id INT AUTO_INCREMENT PRIMARY KEY,
	name TEXT NOT NULL,
	email TEXT NOT NULL
);

CREATE TABLE product (
	id INT AUTO_INCREMENT PRIMARY KEY,
	name TEXT NOT NULL,
	description TEXT NOT NULL,
	price FLOAT NOT NULL
);

CREATE TABLE interest_product_client (
	client INT NOT NULL,
	product INT NOT NULL,
	UNIQUE INDEX client_product (client, product),
	FOREIGN KEY (client) REFERENCES client(id) ON DELETE CASCADE,
	FOREIGN KEY (product) REFERENCES product(id) ON DELETE CASCADE
);

INSERT INTO client (name, email) VALUES ('Prof. Blair Reichel', 'cora75@hotmail.com');
INSERT INTO client (name, email) VALUES ('Kacie McCullough', 'domingo56@yahoo.com');

INSERT INTO product (name, description, price) VALUES ('autem', 'Mystery,\' the Mock Turtle yawned and shut his note-book hastily. \'Consider your verdict,\' he said to the Knave. The Knave did so, very carefully, remarking, \'I really must be a Caucus-race.\' \'What.', '2074.71');
INSERT INTO product (name, description, price) VALUES ('eius', 'I do so like that curious song about the whiting!\' \'Oh, as to prevent its undoing itself,) she carried it out loud. \'Thinking again?\' the Duchess began in a natural way. \'I thought it over a little.', '42633900');
INSERT INTO product (name, description, price) VALUES ('quo', 'Alice asked in a low voice, to the beginning of the house, quite forgetting her promise. \'Treacle,\' said a whiting before.\' \'I can tell you more than three.\' \'Your hair wants cutting,\' said the.', '229204');
INSERT INTO product (name, description, price) VALUES ('ut', 'I\'m sure _I_ shan\'t be beheaded!\' said Alice, who felt ready to agree to everything that was sitting on a branch of a tree in the same thing as \"I get what I say--that\'s the same thing a bit!\' said.', '16272.3');
INSERT INTO product (name, description, price) VALUES ('rerum', 'Then she went out, but it said nothing. \'This here young lady,\' said the Cat: \'we\'re all mad here. I\'m mad. You\'re mad.\' \'How do you know what \"it\" means well enough, when I got up and bawled out,.', '30551600');
INSERT INTO product (name, description, price) VALUES ('repellat', 'Pat, what\'s that in about half no time! Take your choice!\' The Duchess took no notice of her voice, and the moment she quite forgot how to spell \'stupid,\' and that makes them sour--and camomile that.', '39.57');
INSERT INTO product (name, description, price) VALUES ('quo', 'HER ONE, THEY GAVE HIM TWO--\" why, that must be getting home; the night-air doesn\'t suit my throat!\' and a long tail, certainly,\' said Alice very politely; but she did not like to show you! A little.', '9072.88');
INSERT INTO product (name, description, price) VALUES ('laborum', 'Dodo solemnly presented the thimble, saying \'We beg your pardon!\' said the Pigeon in a furious passion, and went on: \'But why did they draw the treacle from?\' \'You can draw water out of sight before.', '193112000');
INSERT INTO product (name, description, price) VALUES ('ut', 'These were the two creatures got so much about a whiting to a mouse, you know. But do cats eat bats? Do cats eat bats, I wonder?\' Alice guessed who it was, even before she found her head through the.', '533.518');
INSERT INTO product (name, description, price) VALUES ('nobis', 'The Queen turned angrily away from her as she spoke. (The unfortunate little Bill had left off sneezing by this time, and was delighted to find any. And yet I wish I could say if I can kick a.', '35.2531');

INSERT INTO interest_product_client (client, product) VALUES (1, 1);
INSERT INTO interest_product_client (client, product) VALUES (1, 3);
INSERT INTO interest_product_client (client, product) VALUES (1, 5);
INSERT INTO interest_product_client (client, product) VALUES (1, 7);
INSERT INTO interest_product_client (client, product) VALUES (1, 9);
INSERT INTO interest_product_client (client, product) VALUES (2, 2);
INSERT INTO interest_product_client (client, product) VALUES (2, 4);
INSERT INTO interest_product_client (client, product) VALUES (2, 6);
INSERT INTO interest_product_client (client, product) VALUES (2, 8);
INSERT INTO interest_product_client (client, product) VALUES (2, 10);

