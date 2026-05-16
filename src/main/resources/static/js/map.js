const sidebar = document.getElementById('sidebar');
const closeBtn = document.getElementById('close-btn');
const productList = document.getElementById('product-list');

const latitude = 50.4501;
const longitude = 30.5234;

const map = L.map('map').setView([latitude, longitude], 13);

L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
	attribution: '© OpenStreetMap'
}).addTo(map);

const marker = L.marker([latitude, longitude]).addTo(map);

marker.on('click', function() {
	sidebar.style.display = 'block';
	productList.innerHTML = 'Загрузка...';

	fetch('/api/v1/products')
		.then(res => res.json())
		.then(data => {
			productList.innerHTML = '';

			data.content.forEach(product => {
				productList.innerHTML += `
				<div class="product-card">
				<img 
						class="product-image"
				           src="${product.imageUrl}"
				       >

				       <br><br>
                    <div class="product-info">
                        <b>${product.name}</b><br>
                        Цена: ${(product.price / 100).toFixed(2)} грн<br>
                    </div>
					</div>
                `;
			});
		});
});

closeBtn.addEventListener('click', function() {
	sidebar.style.display = 'none';
});