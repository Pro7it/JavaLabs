const cards = document.querySelectorAll(".card");

let matchedCard = 0;
let cardOne = null;
let cardTwo = null;
let disableDeck = false;

function flipCard(e) {
    let clickedCard = e.currentTarget;

    if (clickedCard !== cardOne && !disableDeck) {
        clickedCard.classList.add("flip");

        if (!cardOne) {
            cardOne = clickedCard;
            return;
        }

        cardTwo = clickedCard;
        disableDeck = true;

        const cardOneImg = cardOne.querySelector("img").src;
        const cardTwoImg = cardTwo.querySelector("img").src;

        matchCards(cardOneImg, cardTwoImg);
    }
}

function matchCards(img1, img2) {
    if (img1 === img2) {
        matchedCard++;
        cardOne.removeEventListener("click", flipCard);
        cardTwo.removeEventListener("click", flipCard);

        cardOne = null;
        cardTwo = null;
        disableDeck = false;

        if (matchedCard === cards.length / 2) {
            setTimeout(shuffleCards, 1500);
        }
    } else {
        setTimeout(() => {
            cardOne.classList.remove("flip");
            cardTwo.classList.remove("flip");
            cardOne = null;
            cardTwo = null;
            disableDeck = false;
        }, 1000);
    }
}

function shuffleCards() {
    matchedCard = 0;
    cardOne = cardTwo = null;
    disableDeck = false;

    // let images = Array.from({ length: cards.length / 2 }, (_, i) => i + 1);
    images = [1,2,3,4,6,1,2,3,4,6,5,5];
    images.sort(() => Math.random() - 0.5);

    cards.forEach((card, index) => {
        card.classList.remove("flip");
        const img = card.querySelector("img");
        img.src = `card-game/img-${images[index]}.png`;

        card.addEventListener("click", flipCard);
    });
}

shuffleCards();
