//
//  BATopThirdView.swift
//  BudgetingApp
//
//  Created by Aidyn Assan on 13.07.2022.
//

import UIKit

class BATopThirdView: UIView, UICollectionViewDelegate, UICollectionViewDataSource {
    
    var myCatObjects: [(String, String, String, String)] = [] {
        didSet {
            collectionView.reloadData()
        }
    }
    var myCategories: [String] = [] {
        didSet {
            for i in 0..<myCategories.count {
                switch myCategories[i] {
                case Categories.beauty.rawValue: myCatObjects.append(MyFanctions.returnCategories(cat: .beauty))
                case Categories.cinema.rawValue: myCatObjects.append(MyFanctions.returnCategories(cat: .cinema))
                case Categories.clothes.rawValue: myCatObjects.append(MyFanctions.returnCategories(cat: .clothes))
                case Categories.fitness.rawValue: myCatObjects.append(MyFanctions.returnCategories(cat: .fitness))
                case Categories.furniture.rawValue: myCatObjects.append(MyFanctions.returnCategories(cat: .furniture))
                case Categories.games.rawValue: myCatObjects.append(MyFanctions.returnCategories(cat: .games))
                case Categories.medicine.rawValue: myCatObjects.append(MyFanctions.returnCategories(cat: .medicine))
                case Categories.restaurants.rawValue: myCatObjects.append(MyFanctions.returnCategories(cat: .restaurants))
                case Categories.taxi.rawValue: myCatObjects.append(MyFanctions.returnCategories(cat: .taxi))
                case Categories.travel.rawValue: myCatObjects.append(MyFanctions.returnCategories(cat: .travel))
                case Categories.noCat.rawValue: myCatObjects.append(MyFanctions.returnCategories(cat: .noCat))
                default: continue
                }
            }
        }
    }

	let topFirstLabel: UILabel = {
		let label = UILabel()
        label.text = "25000 ₸"
        NetworkManager.shared.getBonus(path: "get-bonus"){ result in
            switch result {
            case .success(let purchases): label.text = "\(Double(Int(100*purchases))/100) B"
            case .failure: label.text = "\(0.0))"
            }
        }
		label.textColor = .systemGreen
		label.translatesAutoresizingMaskIntoConstraints = false
		return label
	}()
	
	let topSecondLabel: UILabel = {
		let label = UILabel()
        NetworkManager.shared.getBonus(path: "maximum-bonus"){ result in
            switch result {
            case .success(let purchases): label.text = "\(Double(Int(100*purchases))/100) B"
            case .failure: label.text = "\(0.0))"
            }
        }
		label.textColor = .systemRed
		label.translatesAutoresizingMaskIntoConstraints = false
		return label
	}()
	
	let centerLabel: UILabel = {
		let label = UILabel()
		label.text = "Лучшая комбинация бонусов"
		label.translatesAutoresizingMaskIntoConstraints = false
		return label
	}()
    
    let collectionView: UICollectionView = {
        let layout = UICollectionViewFlowLayout()
        layout.sectionInset = UIEdgeInsets(top: 2, left: 12, bottom: 2, right: 12)
        layout.itemSize = CGSize(width: 30, height: 30)
        layout.scrollDirection = .horizontal
        let collectionView = UICollectionView(frame: .zero, collectionViewLayout: layout)
        collectionView.translatesAutoresizingMaskIntoConstraints = false
        return collectionView
    }()
	
	override init(frame: CGRect) {
		super.init(frame: frame)
		backgroundColor = .white
		layer.masksToBounds = true
		layer.cornerRadius = 10
		translatesAutoresizingMaskIntoConstraints = false
        collectionView.dataSource = self
        collectionView.delegate = self
        collectionView.register(UICollectionViewCell.self, forCellWithReuseIdentifier: "cell")
		configure()
        getData()
	}
	
	required init?(coder: NSCoder) {
		fatalError("init(coder:) has not been implemented")
	}
	
    func getData() {
        NetworkManager.shared.getCategories(path: "best-combination"){ [weak self] result in
            switch result {
            case .success(let myCats): self?.myCategories = myCats
            case .failure: print("No Data")
            }
        }
    }
    
	func configure() {
		addSubview(centerLabel)
		addSubview(topFirstLabel)
		addSubview(topSecondLabel)
        addSubview(collectionView)
		NSLayoutConstraint.activate([
			centerLabel.centerXAnchor.constraint(equalTo: centerXAnchor),
			centerLabel.centerYAnchor.constraint(equalTo: centerYAnchor),
			
			topFirstLabel.leadingAnchor.constraint(equalTo: centerLabel.leadingAnchor),
			topFirstLabel.bottomAnchor.constraint(equalTo: centerLabel.topAnchor),
			
			topSecondLabel.trailingAnchor.constraint(equalTo: centerLabel.trailingAnchor),
			topSecondLabel.bottomAnchor.constraint(equalTo: centerLabel.topAnchor),
            
            collectionView.leadingAnchor.constraint(equalTo: centerLabel.leadingAnchor, constant: 24),
            collectionView.trailingAnchor.constraint(equalTo: centerLabel.trailingAnchor, constant: -24),
            collectionView.bottomAnchor.constraint(equalTo: bottomAnchor),
            collectionView.topAnchor.constraint(equalTo: centerLabel.bottomAnchor),
		])
	}
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        myCatObjects.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "cell", for: indexPath)
        let image = UIImage(named: myCatObjects[indexPath.row].1)
        let imageView = UIImageView(image: image)
        cell.backgroundView = imageView
        return cell
    }
}
