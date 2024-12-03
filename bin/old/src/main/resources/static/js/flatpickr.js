flatpickr('#reservationsDate', {
	enableTime: false,
	dateFormat: "Y-m-d",
	locale: 'ja'
});

flatpickr('#reservationTime', {
	enableTime: true,
	noCalendar: true,
	dateFormat: "H:i",
	locale: 'ja'
});