//
//  BACollectionViewCell.swift
//  BudgetingApp
//
//  Created by Aidyn Assan on 12.07.2022.
//

import UIKit

class BACollectionViewCell: UICollectionViewCell {
	
	static let cellID = "cellID"
	
	let sectionImageView = UIImageView()
	let sectionTitleLabel = UILabel()
	let sectionBonusLabel = UILabel()
	
	func setup(_ category: Category) {
		sectionTitleLabel.text = category.name
		sectionBonusLabel.text = "\(category.expense) ₸"
		let image = UIImage(systemName: category.imageName)
		sectionImageView.image = image
		sectionImageView.tintColor = category.color
		sectionBonusLabel.textColor = UIColor(named: "orange")
	}
	
	override init(frame: CGRect) {
		super.init(frame: frame)
		configure()
	}
	
	required init?(coder: NSCoder) {
		fatalError("init(coder:) has not been implemented")
	}
	
	func configure() {
		sectionImageView.translatesAutoresizingMaskIntoConstraints = false
		sectionTitleLabel.translatesAutoresizingMaskIntoConstraints = false
		sectionBonusLabel.translatesAutoresizingMaskIntoConstraints = false
		
		sectionTitleLabel.textAlignment = .center
		sectionTitleLabel.font = .systemFont(ofSize: 16)
		sectionTitleLabel.numberOfLines = 0
		sectionBonusLabel.textAlignment = .center
		sectionBonusLabel.font = .systemFont(ofSize: 14)
		
		backgroundColor = .systemGray6
		layer.masksToBounds = true
		layer.cornerRadius = 20
		
		addSubview(sectionImageView)
		addSubview(sectionTitleLabel)
		addSubview(sectionBonusLabel)
		let padding: CGFloat = 20
		NSLayoutConstraint.activate([
			sectionImageView.topAnchor.constraint(equalTo: topAnchor, constant: 5),
			sectionImageView.leadingAnchor.constraint(equalTo: leadingAnchor, constant: padding+5),
			sectionImageView.trailingAnchor.constraint(equalTo: trailingAnchor, constant: -padding-5),
			sectionImageView.heightAnchor.constraint(equalTo: sectionImageView.widthAnchor),
			
			sectionBonusLabel.bottomAnchor.constraint(equalTo: bottomAnchor, constant: -5),
			sectionBonusLabel.leadingAnchor.constraint(equalTo: leadingAnchor),
			sectionBonusLabel.trailingAnchor.constraint(equalTo: trailingAnchor),
			sectionBonusLabel.heightAnchor.constraint(equalToConstant: 10),
			
			sectionTitleLabel.topAnchor.constraint(equalTo: sectionImageView.bottomAnchor),
			sectionTitleLabel.bottomAnchor.constraint(equalTo: sectionBonusLabel.topAnchor),
			sectionTitleLabel.leadingAnchor.constraint(equalTo: leadingAnchor),
			sectionTitleLabel.trailingAnchor.constraint(equalTo: trailingAnchor)
		])
	}
}
