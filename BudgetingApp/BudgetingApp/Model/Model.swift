//
//  Model.swift
//  BudgetingApp
//
//  Created by Olzhas Seiilkhanov on 08.07.2022.
//

import UIKit

struct Category {
	let name: String
	let imageName: String
	var expense: Double
	var bonus: Double
	let color: UIColor
	
	static var categories: [Category] = [
		Category(name: "Такси", imageName: "car.circle.fill", expense: 23214, bonus: 534.5, color: UIColor(named: "orange")!),
		Category(name: "Салоны красоты и косметики", imageName: "sparkles", expense: 12992, bonus: 439.9, color: UIColor(named: "yellow")!),
		Category(name: "Кафе и рестораны", imageName: "fork.knife.circle.fill", expense: 44121, bonus: 4221.6, color: UIColor(named: "mellon")!),
		Category(name: "Медицинские услуги", imageName: "pills.circle.fill", expense: 10023, bonus: 992.2, color: UIColor(named: "salad")!),
		Category(name: "Онлайн кино и музыка", imageName: "music.note.tv.fill", expense: 5933, bonus: 213.1, color: UIColor(named: "azure")!),
		Category(name: "Фитнес и SPA", imageName: "dumbbell.fill", expense: 12023, bonus: 213.2, color: UIColor(named: "red")!),
		Category(name: "Одежда и обувь", imageName: "bag.fill", expense: 65212, bonus: 5322.1, color: UIColor(named: "blue")!),
		Category(name: "Игровые сервисы", imageName: "gamecontroller.fill", expense: 6432, bonus: 12.43, color: UIColor(named: "berry")!),
		Category(name: "Путешествия", imageName: "airplane.circle.fill",
				 expense: 14999, bonus: 123.4, color: UIColor(named: "purple")!),
		Category(name: "Мебель", imageName: "chair.lounge.fill", expense: 12324, bonus: 231.1, color: UIColor(named: "corall")!),
		Category(name: "Другое", imageName: "rays", expense: 31321, bonus: 213.28, color: .systemGray3)
	]
//		.sorted(by: { $0.expense > $1.expense})
}
