const latitude = 50.4501;
const longitude = 30.5234;

const map = L.map('map').setView([latitude, longitude], 13);

L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
	attribution: '© OpenStreetMap'
}).addTo(map);

fetch('/api/v1/products')
	.then(res => res.json())
	.then(data => {
		const products = data.content;

		let html = '<b>Товары:</b><br>';

		products.forEach(product => {
			html += `
                <div>
                    ${product.name}<br>
                    Цена: ${product.price / 100} грн<br>
                    Магазин: ${product.firm}
                    <hr>
                </div>
            `;
		});

		L.marker([latitude, longitude])
			.addTo(map)
			.bindPopup(html)
			.openPopup();
	});